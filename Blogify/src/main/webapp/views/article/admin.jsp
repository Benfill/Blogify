<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<%@page import="model.ArticleModel"%>
<%
  ArticleModel model=new ArticleModel();
  if(request.getAttribute("model") != null){
	 model=(ArticleModel)request.getAttribute("model");
  }
%> 
<layout:dashboard >

    <div class="w-[85%] my-[20px] mx-auto">
        <div id="msgs">
            <c:if test="${not empty param.success}">
                <div class="p-4 mb-4 text-sm text-green-800 rounded-lg bg-green-50 dark:bg-gray-800 dark:text-green-400" role="alert">
                    <span class="font-medium">Success:</span> ${param.success}
                </div>
            </c:if>
            <c:if test="${not empty param.error}">
                <div class="p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400" role="alert">
                    <span class="font-medium">Error:</span> ${param.error}
                </div>
            </c:if>
        </div>
        <div class="my-4 mx-2">
            <a class="bg-sky-700 text-white hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center " href="${pageContext.request.contextPath}/article?action=add">Add new</a>
        </div>

    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" class="px-6 py-3">
                        Id
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Title
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Create At
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Published By
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Status
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Action
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${model.articles}" var="article">
                    <tr class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:bg-gray-800 border-b dark:border-gray-700">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                            ${article.id}
                        </th>
                        <td class="px-6 py-4">
                            ${article.title}
                        </td>
                        <td class="px-6 py-4">
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${article.creationDate}" />
                        </td>
                        <td class="px-6 py-4">
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${article.publishedDateTime}" />
                        </td>
                        <td class="px-6 py-4">
                            ${article.status}
                        </td>
                        <td class="px-6 py-4">
                            <button data-modal-target="popup-modal-${article.id}" data-modal-toggle="popup-modal-${article.id}" class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
                                Delete
                            </button>
                
                            <div id="popup-modal-${article.id}" class="hidden fixed top-0 left-0 right-0 z-50 w-full h-full overflow-y-auto overflow-x-hidden bg-gray-900 bg-opacity-50" tabindex="-1" aria-hidden="true">
                                <div class="relative w-full h-full max-w-md p-4 md:h-auto">
                                    <div class="bg-white rounded-lg shadow dark:bg-gray-700">
                                        <button type="button" class="absolute top-3 right-2.5 text-gray-400 hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 inline-flex items-center" data-modal-hide="popup-modal-${article.id}">
                                            <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1l12 12M1 13L13 1" />
                                            </svg>
                                        </button>
                                        <form action="article?action=delete" method="post" class="p-4 md:p-5 text-center">
                                            <input type="hidden" name="id" value="${article.id}"> 
                                            <svg class="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
                                            </svg>
                                            <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">Are you sure you want to delete this product?</h3>
                                            <button data-modal-hide="popup-modal-${article.id}" type="submit" class="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center">
                                                Yes, I'm sure
                                            </button>
                                            <button data-modal-hide="popup-modal-${article.id}" type="button" class="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700">No, cancel</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                


        

            
            </tbody>
        </table>

       
        
    </div>
     <!-- Pagination -->
     <nav aria-label="Page navigation example" class="flex align-center justify-center m-2">
        <ul class="inline-flex -space-x-px text-base h-10">
          <c:if test="${model.page > 1}">
          <li>
            <a href="${pageContext.request.contextPath}/article?action=admin&page=${model.page - 1}" class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">Previous</a>
          </li>
          </c:if>
          <c:forEach begin="1" end="${model.pageNumbers}" var="pageNum">
          <li>
            <a href="${pageContext.request.contextPath}/article?action=admin&page=${pageNum}" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">${pageNum}</a>
          </li>
          </c:forEach>
          <c:if test="${model.page < model.pageNumbers}">
          <li>
            <a href="${pageContext.request.contextPath}/article?action=admin&page=${model.page + 1}" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">Next</a>
          </li>
          </c:if>
        </ul>
      </nav>

      <!-- Pagination -->

    </div>

   
    <!-- <script src="${pageContext.request.contextPath}/script/index.js"></script> -->

</layout:dashboard>
