<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Club List</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        /* Style for container and task bar */
        .container {
            width: 80%;
            margin: 0 auto;
        }

        .task-bar {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
            align-items: center;
        }

        .search-container {
            display: flex;
            gap: 10px;
        }

        .search-container input {
            padding: 5px;
            font-size: 16px;
        }

        .search-container button {
            padding: 5px 10px;
            font-size: 16px;
            background-color: white;
            color: black;
            border: 1px solid #00b5e2;
            cursor: pointer;
            border-radius: 8px;
        }

        .search-container button:hover {
            background-color: #e2e6ea;
        }

        .action-buttons button {
            padding: 5px 10px;
            font-size: 16px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 8px;
        }

        .action-buttons button:hover {
            background-color: #218838;
        }

        /* Style for the table */
        #clubTable {
            width: 100%;
            border-collapse: collapse;
        }

        #clubTable th, #clubTable td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        #clubTable th {
            background-color: #f4f4f4;
        }

        .action-link {
            color: #007BFF;
            text-decoration: underline;
            cursor: pointer;
        }

        .action-link:hover {
            color: #0056b3;
        }

        /* Modal Styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fff;
            margin: 5% auto;
            padding: 20px;
            border-radius: 8px;
            width: 80%;
            max-width: 500px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .modal input, .modal textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .modal button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .modal button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Club List</h1>

        <!-- Task Bar -->
        <div class="task-bar">
            <div class="action-buttons">
                <button id="createClubButton" onclick="openCreateClubModal()">Create Club</button>
            </div>
            <div class="search-container">
                <input type="text" id="searchBar" placeholder="Search...">
                <button id="searchButton" onclick="filterClubs()">Search</button>
            </div>
        </div>

        <!-- Club List Table -->
        <table id="clubTable">
            <thead>
                <tr>
                    <th>Club ID</th>
                    <th>Club Name</th>
                    <th>Description</th>
                    <th>Created Date</th>
                    <th>Public</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Photography Club</td>
                    <td>For photography enthusiasts</td>
                    <td>2023-06-15</td>
                    <td>Yes</td>
                    <td><span class="action-link" onclick="openUpdateClubModal(1)">Update</span></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Chess Club</td>
                    <td>Chess tournaments and practice</td>
                    <td>2023-04-10</td>
                    <td>No</td>
                    <td><span class="action-link" onclick="openUpdateClubModal(2)">Update</span></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Coding Club</td>
                    <td>Learn to code and build projects</td>
                    <td>2023-02-25</td>
                    <td>Yes</td>
                    <td><span class="action-link" onclick="openUpdateClubModal(3)">Update</span></td>
                </tr>
            </tbody>
        </table>
    </div>

   <!-- Create Club Modal -->
<div id="createClubModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeCreateClubModal()">&times;</span>
        <h2>Create Club</h2>
        <form id="createClubForm">
            <input type="text" id="clubName" placeholder="Club Name" required>
            <textarea id="clubDescription" placeholder="Description" required></textarea>
            <input type="date" id="clubDate" required readonly>
            <label for="isPublic">Public: </label>
            <input type="checkbox" id="isPublic">
            <button type="submit" onclick="submitCreateClub()">Create Club</button>
        </form>
    </div>
</div>

<!-- Update Club Modal -->
<div id="updateClubModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeUpdateClubModal()">&times;</span>
        <h2>Update Club</h2>
        <form id="updateClubForm">
            <input type="text" id="updateClubName" placeholder="Club Name" required>
            <textarea id="updateClubDescription" placeholder="Description" required></textarea>
            <input type="date" id="updateClubDate" required readonly>
            <label for="updateIsPublic">Public: </label>
            <input type="checkbox" id="updateIsPublic">
            <button type="submit" onclick="submitUpdateClub()">Update Club</button>
        </form>
    </div>
</div>

    <script>
        // Open Create Club Modal
        function openCreateClubModal() {
            document.getElementById("createClubModal").style.display = "block";
            // Set the current date for Create Club
            document.getElementById("clubDate").value = new Date().toISOString().split('T')[0];
        }

        // Close Create Club Modal
        function closeCreateClubModal() {
            document.getElementById("createClubModal").style.display = "none";
        }

        // Open Update Club Modal
        function openUpdateClubModal(clubId) {
            document.getElementById("updateClubModal").style.display = "block";
            // For demo purposes, populate the fields with sample data based on clubId
            // In a real scenario, you would fetch data for the specific clubId from your backend
            document.getElementById("updateClubName").value = "Club " + clubId;
            document.getElementById("updateClubDescription").value = "Description of Club " + clubId;
            document.getElementById("updateClubDate").value = new Date().toISOString().split('T')[0]; // Set today's date
            document.getElementById("updateIsPublic").checked = clubId % 2 === 0; // Example condition for public status
        }

        // Close Update Club Modal
        function closeUpdateClubModal() {
            document.getElementById("updateClubModal").style.display = "none";
        }

        // Submit Create Club
        function submitCreateClub(event) {
            event.preventDefault();  // Prevent form submission
            alert('Create Club functionality not implemented yet!');
            closeCreateClubModal();
        }

        // Submit Update Club
        function submitUpdateClub(event) {
            event.preventDefault();  // Prevent form submission
            alert('Update Club functionality not implemented yet!');
            closeUpdateClubModal();
        }

        // Filter Clubs based on Search Input
        function filterClubs() {
            let filter = document.getElementById("searchBar").value.toLowerCase();
            let rows = document.querySelectorAll("#clubTable tbody tr");
            rows.forEach(row => {
                let clubName = row.cells[1].innerText.toLowerCase();
                if (clubName.includes(filter)) {
                    row.style.display = "";
                } else {
                    row.style.display = "none";
                }
            });
        }
    </script>

</body>
</html>
