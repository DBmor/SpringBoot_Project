## 🚀 Quick Start Guide - Food Review System

### Step 1: MySQL Database Setup
```sql
CREATE DATABASE student_db;
```

### Step 2: Update Database Credentials
Edit: `src/main/resources/application.properties`

```properties
spring.datasource.username=root
spring.datasource.password=root
```

### Step 3: Build Project
```bash
mvn clean install
```

### Step 4: Run Application
```bash
mvn spring-boot:run
```

### Step 5: Access Application
Open your browser and go to:
```
http://localhost:8080
```

---

## ✨ What You Get

✅ **12 Sample Menu Items** - Automatically loaded on first run
- Vegetarian dishes (Paneer Butter Masala, Dal Makhani, Aloo Gobi)
- Non-Vegetarian (Butter Chicken, Tandoori Chicken, Fish Curry)
- Vegan options (Chana Masala, Vegetable Biryani)
- Desserts (Gulab Jamun, Kheer)
- Beverages (Masala Chai, Mango Lassi)

✅ **No Login Required** - Direct access to menu, reviews, and suggestions

✅ **5-Star Review System** - Students can rate and comment on food items

✅ **Suggestion System** - Submit feedback for new items or improvements

✅ **Search & Filter** - Find items by name or category

✅ **Responsive Design** - Works on desktop and mobile

---

## 🎯 Features

### Menu Browsing
- View all food items with details
- See average ratings
- Filter by category
- Search by name

### Review System
- Add 5-star ratings
- Leave text comments
- View all reviews
- See average rating per item

### Suggestions
- Submit new item ideas
- Suggest menu improvements
- General feedback
- Track submission status

---

## 📱 Pages

**Menu Tab**
- Browse all available items
- Click on any item for details
- Add reviews directly from cards or detail view
- Search and filter options

**Suggestions Tab**
- Submit suggestions without login
- View all submitted suggestions
- Track whether suggestions are resolved

---

## 🔌 API Endpoints

```
GET  /api/menu                    → Get all menu items
POST /api/review                  → Submit review
GET  /api/review/menu/{id}        → Get reviews for item
POST /api/suggestion              → Submit suggestion
GET  /api/suggestion              → Get all suggestions
```

---

## 📋 Database Tables

| Table | Purpose |
|-------|---------|
| menu_items | Food items with price, category, ratings |
| reviews | Student reviews and ratings |
| suggestions | Menu suggestions and feedback |

---

## ❓ Troubleshooting

**Error: Connection refused**
- Make sure MySQL is running
- Check credentials in application.properties

**Error: Port 8080 already in use**
- Change `server.port=8081` in application.properties

**No menu items appearing**
- Check that DataInitializer ran (check logs)
- Verify database was created successfully

---

## 🎓 Project Structure

```
StudentApplication.java          → Main Spring Boot app
MenuItemController.java          → API for menu items
ReviewController.java            → API for reviews
SuggestionController.java        → API for suggestions
index.html                       → Frontend page
style.css                        → Styling
main.js                          → Frontend logic
```

---

**That's it! Your Food Review System is ready to use! 🍽️⭐**
