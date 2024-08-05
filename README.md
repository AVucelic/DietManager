# DietManager

DietManager is a comprehensive application designed to help users manage their diet and nutrition. It allows users to track their meals, calculate nutritional values, set dietary goals, and log exercises.

## Features

- **Meal Tracking**: Log and keep track of daily meals.
- **Nutritional Analysis**: Calculate the nutritional values of meals.
- **Goal Setting**: Set and track dietary goals.
- **User Authentication**: Secure login and user management.
- **Exercise Logging**: Select and record exercises, and log calories expended via exercise.
- **Exercise Database Management**: Add new exercises to the database.
- **Date Navigation**: Read logs from different days, and select exercises for any day.
- **Persistent Storage**: Save log and exercise data to CSV files.

## Installation

### Prerequisites

- Python 3.7+
- Pip (Python package installer)
- Virtual environment (recommended)

### Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/AVucelic/DietManager.git
    cd DietManager
    ```

2. **Create a virtual environment** (optional but recommended):
    ```bash
    python -m venv venv
    source venv/bin/activate   # On Windows use `venv\Scripts\activate`
    ```

3. **Install dependencies**:
    ```bash
    pip install -r requirements.txt
    ```

4. **Set up the database**:
    ```bash
    python manage.py migrate
    ```

5. **Create a superuser**:
    ```bash
    python manage.py createsuperuser
    ```

6. **Run the server**:
    ```bash
    python manage.py runserver
    ```

7. **Access the application**:
    Open your web browser and go to `http://127.0.0.1:8000/`

## Usage

### Logging Meals

1. Log in to your account.
2. Navigate to the "Log Meal" section.
3. Enter the details of your meal, including the type of meal and the food items.
4. Save the entry to update your meal log and nutritional analysis.

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

## Contributing

We welcome contributions to improve DietManager. Here’s how you can help:

1. **Fork the repository**:
    Click the "Fork" button at the top right corner of this page to create a copy of this repository in your account.

2. **Clone your fork**:
    ```bash
    git clone https://github.com/your-username/DietManager.git
    cd DietManager
    ```

3. **Create a branch**:
    ```bash
    git checkout -b feature-name
    ```

4. **Make your changes** and commit them:
    ```bash
    git commit -m "Description of your feature"
    ```

5. **Push to your fork**:
    ```bash
    git push origin feature-name
    ```

6. **Create a pull request**:
    Go to the original repository and create a new pull request from your forked repository and branch.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact the repository owner.

---

Thank you for using DietManager! We hope it helps you achieve your dietary and fitness goals.
