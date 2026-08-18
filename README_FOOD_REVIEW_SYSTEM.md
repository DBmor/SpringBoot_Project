# Student Food Review & Suggestion System

A Spring Boot application where students can view the campus food menu, leave reviews and ratings for food items, and submit suggestions for menu improvements.

## Features

✨ **Menu Management**
- Browse all food items without login
- View food details (name, description, price, category)
- Filter by category (Vegetarian, Non-Vegetarian, Vegan, Dessert, Beverage)
- Search food items
- See average ratings for each item

⭐ **Review System**
- Add 5-star ratings for food items
- Leave text comments/reviews
- View all reviews from other students
- See reviewer names and dates

💡 **Suggestion System**
- Submit food suggestions without login
- Suggest new items or improvements
- Categorize suggestions (New Item, Improvement, General)
- View submitted suggestions

## Tech Stack

**Backend:**
- Spring Boot 4.1.0
- Spring Data JPA
- MySQL Database
- Lombok

**Frontend:**
- HTML5
- CSS3 (with responsive design)
- Vanilla JavaScript (ES6+)

## Prerequisites

1. **Java**: JDK 25+
2. **MySQL**: Version 8.0+ installed and running
3. **Maven**: For building the project

## Setup Instructions

### 1. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE student_db;
```

**Note:** The application will automatically create tables using JPA's `ddl-auto=update` setting.

### 2. Configuration

Edit `src/main/resources/application.properties`:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

Update the username and password if needed.

### 3. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. First Run

On the first run, the `DataInitializer` will automatically populate the database with 12 sample menu items:
- 3 Vegetarian items
- 3 Non-Vegetarian items
- 2 Vegan items
- 2 Desserts
- 2 Beverages

## API Endpoints

### Menu Items
- `GET /api/menu` - Get all menu items
- `GET /api/menu/{id}` - Get specific menu item
- `GET /api/menu/category/{category}` - Get items by category
- `POST /api/menu` - Create new menu item
- `PUT /api/menu/{id}` - Update menu item
- `DELETE /api/menu/{id}` - Delete menu item

### Reviews
- `GET /api/review/menu/{menuItemId}` - Get reviews for an item
- `GET /api/review/{id}` - Get specific review
- `POST /api/review` - Submit new review
- `PUT /api/review/{id}` - Update review
- `DELETE /api/review/{id}` - Delete review

### Suggestions
- `GET /api/suggestion` - Get all suggestions
- `GET /api/suggestion/unresolved` - Get unresolved suggestions
- `GET /api/suggestion/{id}` - Get specific suggestion
- `POST /api/suggestion` - Submit new suggestion
- `PUT /api/suggestion/{id}` - Update suggestion
- `DELETE /api/suggestion/{id}` - Delete suggestion

## Database Schema

### menu_items
```
- id (Long, Primary Key)
- name (String)
- description (String)
- price (Double)
- category (String)
- image_url (String)
- avg_rating (Double)
- created_at (LocalDateTime)
```

### reviews
```
- id (Long, Primary Key)
- menu_item_id (Long, Foreign Key)
- student_name (String)
- student_email (String)
- rating (Integer, 1-5)
- comment (String)
- created_at (LocalDateTime)
```

### suggestions
```
- id (Long, Primary Key)
- student_name (String)
- student_email (String)
- suggestion (String)
- description (String)
- category (String)
- is_resolved (Boolean)
- created_at (LocalDateTime)
```

## Usage Guide

### Viewing Menu
1. Open http://localhost:8080 in your browser
2. Browse through all food items
3. Use the search box to find items by name
4. Filter by category from the dropdown

### Adding a Review
1. Click "Add Review" on any food item
2. Enter your name and email
3. Select a rating (1-5 stars)
4. Add a comment (optional)
5. Submit the review
6. Your review will appear in the item's review section

### Viewing Item Details
1. Click on any menu card
2. See the full description, price, and all reviews
3. Add your own review from the details view

### Submitting a Suggestion
1. Go to "Suggestions" tab
2. Fill in your details (name, email)
3. Choose a category
4. Write your suggestion
5. Add description (optional)
6. Submit
7. See your suggestion appear in the suggestions list

## File Structure

```
src/
├── main/
│   ├── java/com/example/student/
│   │   ├── controller/
│   │   │   ├── MenuItemController.java
│   │   │   ├── ReviewController.java
│   │   │   ├── SuggestionController.java
│   │   │   └── PageController.java
│   │   ├── model/
│   │   │   ├── MenuItem.java
│   │   │   ├── Review.java
│   │   │   └── Suggestion.java
│   │   ├── repository/
│   │   │   ├── MenuItemRepository.java
│   │   │   ├── ReviewRepository.java
│   │   │   └── SuggestionRepository.java
│   │   ├── service/
│   │   │   ├── MenuItemService.java
│   │   │   ├── ReviewService.java
│   │   │   └── SuggestionService.java
│   │   ├── init/
│   │   │   └── DataInitializer.java
│   │   └── StudentApplication.java
│   └── resources/
│       ├── templates/
│       │   └── index.html
│       ├── static/
│       │   ├── css/
│       │   │   └── style.css
│       │   └── js/
│       │       └── main.js
│       └── application.properties
└── test/
```

## Troubleshooting

### Connection Refused
- Ensure MySQL server is running
- Check database credentials in application.properties
- Verify database name matches

### Port Already in Use
Change the port in application.properties:
```properties
server.port=8081
```

### Table Not Created
- Check MySQL database exists
- Ensure Spring JPA property: `spring.jpa.hibernate.ddl-auto=update`
- Check application logs for errors

## Future Enhancements

- [ ] User authentication and login
- [ ] Admin panel for menu management
- [ ] Image upload for menu items
- [ ] Email notifications
- [ ] Better analytics and statistics
- [ ] Mobile app version
- [ ] Payment integration
- [ ] Order history

## License

This project is open source and available under the MIT License.

## Support

For issues or questions, please create an issue in the repository.

---

**Happy Reviewing! 🍽️⭐**
