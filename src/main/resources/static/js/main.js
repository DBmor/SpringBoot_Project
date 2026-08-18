// API Base URL
const API_URL = 'http://localhost:8080/api';

// Global variables
let allMenuItems = [];
let currentMenuItems = [];
let selectedRating = 0;

// Initialize on page load
document.addEventListener('DOMContentLoaded', () => {
    initializeEventListeners();
    loadMenuItems();
    loadSuggestions();
});

// ==================== Event Listeners ====================

function initializeEventListeners() {
    // Tab navigation
    document.querySelectorAll('.tab-button').forEach(button => {
        button.addEventListener('click', handleTabChange);
    });

    // Search and filter
    document.getElementById('searchInput').addEventListener('input', filterMenuItems);
    document.getElementById('categoryFilter').addEventListener('change', filterMenuItems);

    // Review form
    document.getElementById('reviewForm').addEventListener('submit', submitReview);

    // Suggestion form
    document.getElementById('suggestionForm').addEventListener('submit', submitSuggestion);

    // Star rating
    document.querySelectorAll('.star').forEach(star => {
        star.addEventListener('click', selectRating);
    });

    // Modal close buttons
    document.querySelectorAll('.close').forEach(closeBtn => {
        closeBtn.addEventListener('click', closeModals);
    });

    // Close modal when clicking outside
    window.addEventListener('click', (e) => {
        const reviewModal = document.getElementById('reviewModal');
        const itemModal = document.getElementById('itemModal');
        if (e.target === reviewModal) reviewModal.classList.remove('show');
        if (e.target === itemModal) itemModal.classList.remove('show');
    });
}

// ==================== Tab Management ====================

function handleTabChange(e) {
    const tabName = e.target.getAttribute('data-tab');
    
    // Update buttons
    document.querySelectorAll('.tab-button').forEach(btn => btn.classList.remove('active'));
    e.target.classList.add('active');
    
    // Update content
    document.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
    document.getElementById(tabName).classList.add('active');
}

// ==================== Menu Management ====================

async function loadMenuItems() {
    try {
        const response = await fetch(`${API_URL}/menu`);
        allMenuItems = await response.json();
        currentMenuItems = allMenuItems;
        displayMenuItems(currentMenuItems);
    } catch (error) {
        console.error('Error loading menu items:', error);
        showError('Failed to load menu items');
    }
}

function displayMenuItems(items) {
    const container = document.getElementById('menuContainer');
    
    if (items.length === 0) {
        container.innerHTML = '<div class="empty-state"><div class="empty-state-icon">🍽️</div><div class="empty-state-text">No menu items found</div></div>';
        return;
    }
    
    container.innerHTML = items.map(item => `
        <div class="menu-card" onclick="viewItemDetails(${item.id})">
            <div class="menu-card-image">
                ${item.imageUrl ? `<img src="${item.imageUrl}" alt="${item.name}">` : '🍽️'}
            </div>
            <div class="menu-card-content">
                <div class="menu-card-name">${item.name}</div>
                <div class="menu-card-category">${item.category || 'General'}</div>
                <div class="menu-card-description">${item.description || 'No description'}</div>
                <div class="menu-card-footer">
                    <div class="menu-card-price">₹${item.price}</div>
                    <div class="menu-card-rating">
                        <span class="stars">${renderStars(item.avgRating)}</span>
                        <span class="count">${item.avgRating ? item.avgRating.toFixed(1) : 'No'} ratings</span>
                    </div>
                </div>
                <button class="btn-review" onclick="openReviewModal(event, ${item.id}, '${item.name}')">Add Review</button>
            </div>
        </div>
    `).join('');
}

function renderStars(rating) {
    if (!rating) return '☆☆☆☆☆';
    const full = Math.floor(rating);
    const empty = 5 - full;
    return '★'.repeat(full) + '☆'.repeat(empty);
}

function filterMenuItems() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const category = document.getElementById('categoryFilter').value;
    
    currentMenuItems = allMenuItems.filter(item => {
        const matchesSearch = item.name.toLowerCase().includes(searchTerm) || 
                            (item.description && item.description.toLowerCase().includes(searchTerm));
        const matchesCategory = !category || item.category === category;
        return matchesSearch && matchesCategory;
    });
    
    displayMenuItems(currentMenuItems);
}

// ==================== Item Details ====================

async function viewItemDetails(itemId) {
    try {
        const itemResponse = await fetch(`${API_URL}/menu/${itemId}`);
        const item = await itemResponse.json();
        
        const reviewsResponse = await fetch(`${API_URL}/review/menu/${itemId}`);
        const reviews = await reviewsResponse.json();
        
        const detailsHtml = `
            <div class="item-details-header">
                <div class="item-image-container">
                    <div class="item-image">
                        ${item.imageUrl ? `<img src="${item.imageUrl}" style="width:100%; height:100%; object-fit:cover;" alt="${item.name}">` : '🍽️'}
                    </div>
                </div>
                <div class="item-info">
                    <h2>${item.name}</h2>
                    <div class="item-meta">
                        <div class="meta-item">
                            <div class="meta-label">Price</div>
                            <div class="meta-value">₹${item.price}</div>
                        </div>
                        <div class="meta-item">
                            <div class="meta-label">Category</div>
                            <div class="meta-value">${item.category || 'General'}</div>
                        </div>
                        <div class="meta-item">
                            <div class="meta-label">Average Rating</div>
                            <div class="meta-value">${item.avgRating ? item.avgRating.toFixed(1) : 'No'} ${renderStars(item.avgRating)}</div>
                        </div>
                        <div class="meta-item">
                            <div class="meta-label">Total Reviews</div>
                            <div class="meta-value">${reviews.length}</div>
                        </div>
                    </div>
                    <div class="item-description">${item.description || 'No description available'}</div>
                    <button class="btn-submit" onclick="openReviewModalFromDetail(${item.id}, '${item.name}')">Add Your Review</button>
                </div>
            </div>
            
            <div style="margin-top: 40px;">
                <h3 style="color: #333; margin-bottom: 20px; font-size: 1.5em;">Student Reviews</h3>
                ${reviews.length === 0 ? 
                    '<div class="empty-state"><div class="empty-state-text">No reviews yet. Be the first to review!</div></div>' :
                    reviews.map(review => `
                        <div class="review-item">
                            <div class="review-item-header">
                                <div>
                                    <div class="review-author">${review.studentName}</div>
                                    <div style="font-size: 0.85em; color: #999;">${review.studentEmail}</div>
                                </div>
                                <div class="review-rating">${renderStars(review.rating)}</div>
                            </div>
                            <div class="review-comment">${review.comment || 'No comment provided'}</div>
                            <div class="review-date">${new Date(review.createdAt).toLocaleDateString()}</div>
                        </div>
                    `).join('')
                }
            </div>
        `;
        
        document.getElementById('itemDetails').innerHTML = detailsHtml;
        document.getElementById('itemModal').classList.add('show');
    } catch (error) {
        console.error('Error loading item details:', error);
        showError('Failed to load item details');
    }
}

// ==================== Review Management ====================

function openReviewModal(e, itemId, itemName) {
    e.stopPropagation();
    document.getElementById('menuItemId').value = itemId;
    document.getElementById('modalTitle').textContent = `Review: ${itemName}`;
    resetReviewForm();
    document.getElementById('reviewModal').classList.add('show');
}

function openReviewModalFromDetail(itemId, itemName) {
    document.getElementById('menuItemId').value = itemId;
    document.getElementById('modalTitle').textContent = `Review: ${itemName}`;
    resetReviewForm();
    document.getElementById('reviewModal').classList.add('show');
}

function resetReviewForm() {
    document.getElementById('reviewForm').reset();
    selectedRating = 0;
    document.querySelectorAll('.star').forEach(star => star.classList.remove('active'));
}

function selectRating(e) {
    selectedRating = parseInt(e.target.getAttribute('data-rating'));
    document.querySelectorAll('.star').forEach(star => {
        const rating = parseInt(star.getAttribute('data-rating'));
        star.classList.toggle('active', rating <= selectedRating);
    });
    document.getElementById('rating').value = selectedRating;
}

async function submitReview(e) {
    e.preventDefault();
    
    if (selectedRating === 0) {
        showError('Please select a rating');
        return;
    }
    
    const menuItemId = document.getElementById('menuItemId').value;
    
    // Fetch the MenuItem object
    try {
        const menuItemResponse = await fetch(`${API_URL}/menu/${menuItemId}`);
        const menuItem = await menuItemResponse.json();
        
        const review = {
            studentName: document.getElementById('reviewName').value,
            studentEmail: document.getElementById('reviewEmail').value,
            rating: selectedRating,
            comment: document.getElementById('comment').value,
            menuItem: menuItem
        };
        
        const response = await fetch(`${API_URL}/review`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(review)
        });
        
        if (response.ok) {
            showSuccess('Review submitted successfully! Thank you!');
            document.getElementById('reviewModal').classList.remove('show');
            resetReviewForm();
            loadMenuItems();
        } else {
            showError('Failed to submit review');
        }
    } catch (error) {
        console.error('Error submitting review:', error);
        showError('Error submitting review');
    }
}

// ==================== Suggestion Management ====================

async function loadSuggestions() {
    try {
        const response = await fetch(`${API_URL}/suggestion`);
        const suggestions = await response.json();
        displaySuggestions(suggestions);
    } catch (error) {
        console.error('Error loading suggestions:', error);
    }
}

function displaySuggestions(suggestions) {
    const container = document.getElementById('suggestionsList');
    
    if (suggestions.length === 0) {
        container.innerHTML = '<div class="empty-state" style="margin-top: 30px;"><div class="empty-state-text">No suggestions yet. Submit your first suggestion!</div></div>';
        return;
    }
    
    container.innerHTML = suggestions.map(suggestion => `
        <div class="suggestion-item">
            <h4>${suggestion.suggestion}</h4>
            <p><strong>By:</strong> ${suggestion.studentName} (${suggestion.studentEmail})</p>
            <p>${suggestion.description || ''}</p>
            <span class="suggestion-category">${suggestion.category}</span>
            ${suggestion.isResolved ? '<span style="margin-left: 10px; color: green;">✓ Resolved</span>' : '<span style="margin-left: 10px; color: #ffc107;">⏳ Pending</span>'}
        </div>
    `).join('');
}

async function submitSuggestion(e) {
    e.preventDefault();
    
    const suggestion = {
        studentName: document.getElementById('sugName').value,
        studentEmail: document.getElementById('sugEmail').value,
        suggestion: document.getElementById('sugText').value,
        description: document.getElementById('sugDesc').value,
        category: document.getElementById('sugCategory').value
    };
    
    try {
        const response = await fetch(`${API_URL}/suggestion`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(suggestion)
        });
        
        if (response.ok) {
            showSuccess('Thank you! Your suggestion has been submitted.');
            document.getElementById('suggestionForm').reset();
            loadSuggestions();
        } else {
            showError('Failed to submit suggestion');
        }
    } catch (error) {
        console.error('Error submitting suggestion:', error);
        showError('Error submitting suggestion');
    }
}

// ==================== Modal Management ====================

function closeModals(e) {
    e.stopPropagation();
    document.getElementById('reviewModal').classList.remove('show');
    document.getElementById('itemModal').classList.remove('show');
}

// ==================== Notifications ====================

function showSuccess(message) {
    const alert = document.createElement('div');
    alert.className = 'alert alert-success';
    alert.textContent = message;
    
    const container = document.querySelector('.container');
    container.insertBefore(alert, container.firstChild);
    
    setTimeout(() => alert.remove(), 4000);
}

function showError(message) {
    const alert = document.createElement('div');
    alert.className = 'alert alert-error';
    alert.textContent = message;
    
    const container = document.querySelector('.container');
    container.insertBefore(alert, container.firstChild);
    
    setTimeout(() => alert.remove(), 4000);
}
