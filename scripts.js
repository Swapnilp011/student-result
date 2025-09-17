$(document).ready(function() {
    const apiUrl = 'http://localhost:8080/api'; // Assuming backend is running on port 8080
    let loggedInStudentId = null;

    // Switch to login form initially
    $('#login-section').show();
    $('#register-section').hide();
    $('#marks-section').hide();

    // Switch between login and register forms
    $('#login-form').on('submit', function(e) {
        e.preventDefault();
        login();
    });

    $('#register-form').on('submit', function(e) {
        e.preventDefault();
        register();
    });

    $('#marks-form').on('submit', function(e) {
        e.preventDefault();
        enterMarks();
    });

    $('#logout-btn').on('click', function() {
        logout();
    });

    $('#show-average-btn').on('click', function() {
        showStudentAverage();
    });

    $('#show-topper-btn').on('click', function() {
        showTopper();
    });

    function register() {
        const username = $('#reg-username').val();
        const password = $('#reg-password').val();

        $.ajax({
            url: `${apiUrl}/students/register`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ username, password }),
            success: function(response) {
                alert('Registration successful! Please login.');
                $('#login-section').show();
                $('#register-section').hide();
            },
            error: function(xhr, status, error) {
                console.error("Registration failed. Status:", status, "Error:", error, "Response:", xhr.responseText);
                alert('Registration failed: ' + (xhr.responseText || 'No response from server. Check if the backend is running and if CORS is configured correctly.'));
            }
        });
    }

    function login() {
        const username = $('#login-username').val();
        const password = $('#login-password').val();

        $.ajax({
            url: `${apiUrl}/students/login`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ username, password }),
            success: function(response) {
                loggedInStudentId = response.studentId; // Assuming backend returns studentId
                $('#login-section').hide();
                $('#marks-section').show();
                alert('Login successful!');
            },
            error: function(xhr, status, error) {
                console.error("Login failed. Status:", status, "Error:", error, "Response:", xhr.responseText);
                alert('Login failed: ' + (xhr.responseText || 'No response from server. Check if the backend is running and if CORS is configured correctly.'));
            }
        });
    }

    function logout() {
        loggedInStudentId = null;
        $('#login-section').show();
        $('#marks-section').hide();
        alert('Logged out successfully.');
    }

    function enterMarks() {
        if (!loggedInStudentId) {
            alert('Please login first.');
            return;
        }

        const subject = $('#subject').val();
        const score = $('#score').val();

        $.ajax({
            url: `${apiUrl}/marks`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ studentId: loggedInStudentId, subject, score }),
            success: function(response) {
                alert('Marks entered successfully!');
                $('#marks-form')[0].reset();
            },
            error: function(xhr, status, error) {
                console.error("Failed to enter marks. Status:", status, "Error:", error, "Response:", xhr.responseText);
                alert('Failed to enter marks: ' + (xhr.responseText || 'No response from server. Check if the backend is running and if CORS is configured correctly.'));
            }
        });
    }

    function showStudentAverage() {
        if (!loggedInStudentId) {
            alert('Please login first.');
            return;
        }

        $.ajax({
            url: `${apiUrl}/students/${loggedInStudentId}/average`,
            type: 'GET',
            success: function(response) {
                $('#average-display').text(`Your average score is: ${response.average.toFixed(2)}`);
            },
            error: function(xhr, status, error) {
                $('#average-display').text('Could not retrieve average score.');
            }
        });
    }

    function showTopper() {
        $.ajax({
            url: `${apiUrl}/students/topper`,
            type: 'GET',
            success: function(response) {
                $('#topper-display').html(`The Topper is <b>${response.username}</b> with an average score of ${response.average.toFixed(2)}`);
            },
            error: function(xhr, status, error) {
                $('#topper-display').text('Could not retrieve topper information.');
            }
        });
    }
});