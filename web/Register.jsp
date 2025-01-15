<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT University Club - Sign Up</title>
    <link href="css/Register.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container">
        <!-- Left Section -->
        <div class="left-section">
            <h1>FPT University Club</h1>
            <img src="https://via.placeholder.com/400" alt="Club Banner">
            <p>Join us to connect, learn, and grow with fellow students!</p>
        </div>

        <!-- Right Section -->
        <div class="right-section">
            <h2>Sign Up</h2>
            <form>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" placeholder="Enter your username" required>
                </div>

                <div class="form-group password-toggle">
                    <label for="password">Password</label>
                    <input type="password" id="password" placeholder="Enter new password" required>
                    <span onclick="togglePassword('password')"></span>
                </div>

                <div class="form-group password-toggle">
                    <label for="verification">Verification</label>
                    <input type="password" id="verification" placeholder="Confirm password" required>
                    <span onclick="togglePassword('verification')"></span>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" placeholder="Enter your email" required>
                </div>

                <button type="submit" class="btn">Sign Up</button>

                <div class="divider">OR</div>

                <div class="google-signup">
                    <button type="button">Sign up with Google</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function togglePassword(id) {
            const input = document.getElementById(id);
            if (input.type === 'password') {
                input.type = 'text';
            } else {
                input.type = 'password';
            }
        }
    </script>
</body>
</html>
