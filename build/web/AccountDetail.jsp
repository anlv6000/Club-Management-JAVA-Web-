<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Information</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-blue-400 to-indigo-500 flex items-center justify-center h-screen">

    <div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-lg">
        <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Account Information</h1>

        <form method="post" action="UpdateAccount" class="space-y-4">
            <!-- Account ID (non-editable) -->
            <div>
                <label class="font-medium">Account ID:</label>
                <input type="text" name="accountId" value="${account.id}" readonly 
                       class="w-full border rounded-lg p-2 bg-gray-100 text-gray-600">
            </div>

            <!-- Username -->
            <div>
                <label class="font-medium">Username:</label>
                <input type="text" name="username" value="${account.username}" 
                       class="w-full border rounded-lg p-2">
            </div>

            <!-- Email -->
            <div>
                <label class="font-medium">Email:</label>
                <input type="email" name="email" value="${account.email}" 
                       class="w-full border rounded-lg p-2">
            </div>

            <!-- Role -->
            <div>
                <label class="font-medium">Role:</label>
                <select name="userType" class="w-full border rounded-lg p-2">
                    <option value="Admin" ${account.role == 'Admin' ? 'selected' : ''}>Admin</option>
                    <option value="ClubMember" ${account.role == 'Club Member' ? 'selected' : ''}>Club Member</option>
                    <option value="ClubLeader" ${account.role == 'Club Leader' ? 'selected' : ''}>Club Leader</option>
                    <option value="WebUser" ${account.role == 'Web User' ? 'selected' : ''}>Web User</option>
                </select>
            </div>

            <!-- Status -->
            <div>
                <label class="font-medium">Status:</label>
                <select name="status" class="w-full border rounded-lg p-2">
                    <option value="Active" ${account.status == 'Active' ? 'selected' : ''}>Active</option>
                    <option value="Inactive" ${account.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                </select>
            </div>

            <!-- Created Date (non-editable) -->
            <div>
                <label class="font-medium">Created Date:</label>
                <input type="text" name="accountCreatedDate" value="${account.accountCreatedDate}" readonly 
                       class="w-full border rounded-lg p-2 bg-gray-100 text-gray-600">
            </div>

            <!-- Last Login Date (non-editable) -->
            <div>
                <label class="font-medium">Last Login Date:</label>
                <input type="text" name="lastLoginDate" value="${account.lastLoginDate}" readonly 
                       class="w-full border rounded-lg p-2 bg-gray-100 text-gray-600">
            </div>

            <!-- Buttons -->
            <div class="flex justify-between mt-4">
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">
                    Save Changes
                </button>
                <button type="button" class="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600"
                        onclick="window.location.href='ManageAccount'">
                    Cancel
                </button>
            </div>
        </form>
    </div>

</body>
</html>
