# DietManager

DietManager is a comprehensive application designed to help users manage their diet and nutrition. It allows users to track their meals, calculate nutritional values, set dietary goals, and log exercises.

## Features

- **Meal Tracking**: Log and keep track of daily meals.
- **Nutritional Analysis**: Calculate the nutritional values of meals.
- **Goal Setting**: Set and track dietary goals.
- **Exercise Logging**: Select and record exercises, and log calories expended via exercise.
- **Exercise Database Management**: Add new exercises to the database.
- **Date Navigation**: Read logs from different days, and select exercises for any day.
- **Persistent Storage**: Save log and exercise data to CSV files.

## Installation

### Prerequisites

- Java
- JavaFX


### Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/AVucelic/DietManager.git
    cd DietManager
    ```


## Usage

### Logging Meals

1. Navigate to the "Log Meal" section.
2. Enter the details of your meal, including the type of meal and the food items.
3. Save the entry to update your meal log and nutritional analysis.

### Logging Exercises (Level 2)

1. Navigate to the "Log Exercise" section.
2. Use the date picker to select the current date.
3. Select and record an exercise for the day.
4. The selection is stored in the database (`log.csv`), and the log view is updated with the calories expended.

### Adding New Exercises (Level 3)

1. Go to the "Exercise Database" section.
2. Add a new exercise to the database.
3. The new exercise can now be added to today's log as described in Level 2.

### Navigating and Logging for Different Days (Level 4)

1. Use the date picker to navigate to different days in the log.
2. Select exercises for any day other than today.
3. View and manage logs for different days.

### Persistent Storage (Level 5)

1. The application saves the log and exercise data structures back to `log.csv` and `exercise.csv` files on any change.
2. This ensures that all user interactions with the GUI are stored persistently.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact the repository owner.

---

Thank you for using DietManager! We hope it helps you achieve your dietary and fitness goals.
