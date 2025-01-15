<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT University Club - Sign Up</title>
    <link href="css/Register.css" rel="stylesheet" type="text/css"/>
    <style> .google-signup button {
            background-color: #f5f5f5;
            color: #333;
            border: 1px solid #ccc;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: underline;
        }</style>
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
            <h2>Login</h2>
            <form>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" placeholder="Enter your username" required>
                </div>

                <div class="form-group password-toggle">
                    <label for="password">Password</label>
                    <input type="password" id="password" placeholder="Enter your password" required>
                    <span onclick="togglePassword('password')"></span>
                </div>

               

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" placeholder="Enter your email" required>
                </div>
 <div class="google-signup">
                    <button type="button">Forgot Password or Username</button>
                </div>
                <button type="submit" class="btn">Sign in</button>

                <div class="divider">OR</div>

                <div class="google-signup">
                    <button type="button">Sign in with Google</button>
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
        document.addEventListener("DOMContentLoaded", function() {
        const logoContainer = document.getElementById("logo-container");

        // Create navigation container
        const nav = document.createElement("nav");
        nav.innerHTML = `
            <a href="#">Home</a>
            <a href="PublicClub.jsp">Public Club</a>
            <a href="Login.jsp">Login</a>
            <a href="Register.jsp">Register</a>
        `;

        // Append navigation to logo container
        logoContainer.appendChild(nav);

        // Add style to align navigation inside logo-container
        const style = document.createElement("style");
        style.innerHTML = `
            #logo-container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            #logo-container nav {
                margin-top: 10px;
                display: flex;
                gap: 15px;
            }

            #logo-container nav a {
                text-decoration: none;
                color: #000;
                font-weight: bold;
            }

            #logo-container nav a:hover {
                text-decoration: underline;
            }
        `;
        document.head.appendChild(style);
    });
    </script>
</body>
</html>
