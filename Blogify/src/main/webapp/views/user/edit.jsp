<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags" %>

<dashboard:dashboard>
    <div class="flex items-center justify-center h-screen">
        <form method="post" action="user?action=update" class="max-w-lg w-full bg-white shadow-md rounded-lg p-8">
            <input type="hidden" name="id" value="${user.id}"/>

            <div class="relative z-0 w-full mb-5 group">
                <input type="text" name="first_name" id="floating_first_name"
                       value="${user.firstName}"
                       class="block py-2.5 px-0 w-full text-sm text-black bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                       placeholder=" " required/>
                <label for="floating_first_name"
                       class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-blue-600">
                    First name
                </label>
            </div>

            <div class="relative z-0 w-full mb-5 group">
                <input type="text" name="second_name" id="floating_second_name"
                       value="${user.secondName}"
                       class="block py-2.5 px-0 w-full text-sm text-black bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                       placeholder=" " required/>
                <label for="floating_second_name"
                       class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-blue-600">
                    Second name
                </label>
            </div>

            <div class="relative z-0 w-full mb-5 group">
                <input type="email" name="email" id="floating_email"
                       value="${user.email}"
                       class="block py-2.5 px-0 w-full text-sm text-black bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                       placeholder=" " required/>
                <label for="floating_email"
                       class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-blue-600">
                    Email
                </label>
            </div>

            <div class="relative z-0 w-full mb-5 group">
                <input type="date" name="birth_date" id="floating_birth_date"
                       value="${user.birthDate}"
                       class="block py-2.5 px-0 w-full text-sm text-black bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                       placeholder=" " required/>
                <label for="floating_birth_date"
                       class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-blue-600">
                    Birth Date
                </label>
            </div>

            <c:choose>
                <c:when test="${user.role == 'ADMIN'}">
                    <input type="hidden" name="password" value="">
                </c:when>
                <c:otherwise>
                    <div class="flex items-center pb-4">
                        <input onclick="pwdHandle()" id="pwd-checkbox" type="checkbox" value=""
                               class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2">
                        <label for="pwd-checkbox" class="ml-2 text-sm font-medium text-gray-500 ">Set new Password?</label>
                    </div>

                    <div id="pwdDive" class="relative z-0 w-full mb-5 group hidden">
                        <input type="password" name="password" id="floating_password"
                               value=""
                               class="block py-2.5 px-0 w-full text-sm text-black bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                               placeholder="********"/>
                        <label for="floating_password"
                               class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-blue-600">
                            New Password
                        </label>
                    </div>
                </c:otherwise>
            </c:choose>

            <div class="relative z-0 w-full mb-5 group">
                <c:if test="${user.role != 'ADMIN'}">
                    <select name="role" id="role"
                            class="block py-2.5 px-0 w-full text-sm text-black bg-transparent border-0 border-b-2 border-gray-300 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            required>
                        <option value="EDITOR" ${user.role == 'EDITOR' ? 'selected' : ''}>Editor</option>
                        <option value="CONTRIBUTOR" ${user.role == 'CONTRIBUTOR' ? 'selected' : ''}>Contributor</option>
                    </select>
                </c:if>
                <c:if test="${user.role == 'ADMIN'}">
                    <input type="hidden" value="ADMIN" name="role">
                    <p class="block py-2.5 px-0 w-full text-sm text-black">Admin <span
                            class="text-sm text-gray-600">(can't change admin role)</span></p>
                </c:if>
                <label for="role"
                       class="peer-focus:font-medium absolute text-sm text-gray-500 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-blue-600">
                    Role
                </label>
            </div>

            <button type="submit"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center">
                Update User
            </button>
        </form>
    </div>

    <script>
        let pwdCheck = document.querySelector("#pwd-checkbox");
        let pwdDive = document.querySelector("#pwdDive");

        function pwdHandle() {
            pwdCheck.checked ? pwdDive.classList.remove("hidden") : pwdDive.classList.add("hidden");
        }
    </script>
</dashboard:dashboard>
