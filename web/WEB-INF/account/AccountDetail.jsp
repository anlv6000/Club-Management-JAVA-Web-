
<%@ include file="/WEB-INF/home/sidebar.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Information</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"></script>
    </head>
    <body class="bg-white flex justify-center items-center min-h-screen">

        <!-- Main Content -->
        <div class="bg-white shadow-2xl rounded-2xl p-12 max-w-[70vw] w-full flex flex-col space-y-8 ml-[250px]">


            <!-- Avatar -->
            <div class="flex justify-center items-center">
                <img src="${empty account.imageURL ? 'https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg' : account.imageURL}" 
                     alt="Avatar" 
                     class="w-28 h-28 rounded-full border-4 border-indigo-500 shadow-lg">
            </div>

            <!-- Form -->
            <form method="post" action="UpdateAccount" class="space-y-6">

                <!-- Grid Layout: 2 Columns -->
                <div class="grid grid-cols-2 gap-6">


                    <!-- Username -->
                    <div class="hidden">
                        <label class="font-medium">Account ID:</label>
                        <input type="text" name="accountId" value="${account.id}" readonly 
                               class="w-full border rounded-lg p-2 bg-gray-100 text-gray-600">
                    </div>


                    <!-- Username -->
                    <div>
                        <label class="font-medium">Username:</label>
                        <input type="text" name="username" value="${account.userName}" readonly 
                               class="w-full border rounded-lg p-2">
                    </div>
                    <div>
                        <label class="font-medium">Fullname:</label>
                        <input type="text" name="fullname" value="${account.fullname}" readonly 
                               class="w-full border rounded-lg p-2">
                    </div>
                    <div>
                        <label class="font-medium">Email:</label>
                        <input type="text" name="email" value="${account.email}" readonly 
                               class="w-full border rounded-lg p-2">
                    </div>

                    <!-- Role -->
                    <div>
                        <label class="font-medium">Role:</label>
                        <select name="userType" class="w-full border rounded-lg p-2">
                            <option value="Admin" ${account.role != null && account.role eq 'Admin' ? 'selected' : ''}>Admin</option>
                            <option value="ClubLeader" ${account.role != null && account.role eq 'ClubLeader' ? 'selected' : ''}>Club Leader</option>
                            <option value="ClubMember" ${account.role != null && account.role eq 'ClubMember' ? 'selected' : ''}>Club Member</option>
                            <option value="WebUser" ${account.role != null && account.role eq 'WebUser' ? 'selected' : ''}>Web User</option>
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
                    <div class="hidden">
                        <label class="font-medium">Created Date:</label>
                        <input type="text" name="accountCreatedDate" value="${account.accountCreatedDate}" readonly 
                               class="w-full border rounded-lg p-2 bg-gray-100 text-gray-600">
                    </div>

                    <!-- Last Login Date (non-editable) -->
                    <div class="hidden">
                        <label class="font-medium">Last Login Date:</label>
                        <input type="text" name="lastLoginDate" value="${account.lastLoginDate}" readonly 
                               class="w-full border rounded-lg p-2 bg-gray-100 text-gray-600">
                    </div>
                                <div>
                        <label class="font-medium">Description:</label>
                        <input type="text" name="text" value="${account.text}" 
                               class="w-full border rounded-lg p-2">
                    </div>

                </div> <!-- End Grid -->

                <!-- Buttons -->
                <div class="flex justify-between mt-6">
                    <button type="submit" class="bg-indigo-600 text-white px-6 py-3 rounded-lg shadow-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition flex items-center">
                        <i class="fas fa-save text-white mr-2"></i> Save Changes
                    </button>
                    <button type="button" class="bg-gray-600 text-white px-6 py-3 rounded-lg shadow-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-gray-500 transition flex items-center"
                            onclick="window.location.href = 'ManageAccount'">
                        <i class="fas fa-times text-white mr-2"></i> Cancel
                    </button>
                </div>

            </form>
        </div>

    </body>
</html>
