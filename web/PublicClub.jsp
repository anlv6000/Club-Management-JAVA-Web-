<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Club Detail</title>
    <link rel="stylesheet" href="css/Club.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #f4f4f4, #e8e8e8);
            margin: 0;
            font-family: 'Roboto', sans-serif;
        }

        .form-container {
            background: white;
            padding: 30px;
            border: none;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 80%; /* Updated width to 80% */
            max-width: 1200px; /* Added max-width for larger screens */
            text-align: center;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .form-container:hover {
            transform: translateY(-10px);
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
        }

        h2 {
            font-size: 2.5em;
            color: #333;
            margin-bottom: 20px;
            border-bottom: 2px solid #ffc107;
            display: inline-block;
            padding-bottom: 5px;
            position: relative;
        }

        h2::after {
            content: '';
            position: absolute;
            width: 50px;
            height: 4px;
            background-color: #ffc107;
            bottom: -5px;
            left: 50%;
            transform: translateX(-50%);
        }

        div {
            font-size: 1.2em;
            color: #666;
            margin-bottom: 10px;
        }

        input, select, textarea {
            width: 100%;
            padding: 12px;
            margin: 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        input:focus, select:focus, textarea:focus {
            border-color: #007bff;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.2);
            outline: none;
        }

        button {
            background-color: #ffc107;
            color: black;
            border: none;
            padding: 15px;
            width: 100%;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 5px;
            font-size: 1.2em;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        button:hover {
            background-color: #e0a800;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        .delete-btn:hover {
            background-color: #c82333;
        }

        .back-btn {
            background-color: #007bff;
            color: white;
        }

        .back-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    
    <div class="form-container">
        <h2>Club Details</h2>
        <form>
            <label>Name:</label>
            <div>${detail.clubName}</div><br>

            <label>Description:</label>
            <div>${detail.description}</div><br>
        </form>
        <button class="back-btn" onclick="window.location.href='Public_ClubServlet'">Back to List</button>
    </div>
        
</body>
</html>
