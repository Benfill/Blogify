<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@page import="model.ArticleModel"%>
<%
  ArticleModel model=new ArticleModel();
  if(request.getAttribute("model") != null){
	 model=(ArticleModel)request.getAttribute("model");
  }
%> 
<layout:layout title="Add article">

<div class="w-[40%] my-[20px] mx-auto">
      <!-- Error Message -->
      <c:if test="${not empty model.error}">
      <div style="color: red;">
          <strong>${model.error}</strong>
      </div>
      </c:if>

      <!-- Success Message -->
      <c:if test="${not empty model.success}">
      <div style="color: green;">
          <strong>${model.success}</strong>
      </div>
      </c:if>

    <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" method="POST" action="article?action=create"  enctype="multipart/form-data">
      <div class="mb-4">
        <c:if test="${not empty model.errors['title']}">
        <div style="color: red;">
            <strong>${model.errors['title']}</strong>
        </div>
        </c:if>
        <label class="block text-gray-700 text-sm font-bold mb-2" for="title">
          Title
        </label>
        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"   name="title" id="title" type="text" placeholder="title">
      </div>
      <div class="mb-4">
          <c:if test="${not empty model.errors['content']}">
          <div style="color: red;">
              <strong>${model.errors['content']}</strong>
          </div>
          </c:if>
            <label class="block text-gray-700 text-sm font-bold mb-2" for="content">
            Content
            </label>
            <textarea name="content" id="content" rows="4" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 " placeholder="Write your thoughts here..."></textarea>
        </div>
        <div class="mb-4">
          
            <label class="block text-gray-700 text-sm font-bold mb-2" for="content">
            Image
            </label>
            <input class="block w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 focus:outline-none " id="file_input" name="file" type="file">
        </div>
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="content">
          Status
        </label>
        <select id="status" name="status" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
          <option selected>Choose a status for this article</option>
            <option value="PUBLISHED">PUBLISHED</option>
            <option value="DRAFT">DRAFT</option>
        </select>
      </div>
    
    
      <div class="flex items-center justify-between">
        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
            Submit
        </button>
     
      </div>
    </form>
   
  </div>
</layout:layout>
