<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>


<layout:layout title="Home">

<div class="h-[90vh] mx-auto w-[95%] ">

<form class="max-w-sm mx-auto p-6">
    <div class="mb-5">
      <label for="username-success" class="block mb-2 text-sm font-medium text-slate-600 ">Title</label>
      <input type="text" name="title" class="bg-gray-50 border border-blue-500  text-green-900  text-sm rounded-lg focus:ring-blue-700 focus:border-green-500 block w-full p-2.5  " placeholder="">
      <!-- <input type="text" id="username-success" class="bg-green-50 border border-green-500 text-green-900  text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5  " placeholder="Bonnie Green"> -->
      <!-- <p class="mt-2 text-sm text-green-600 dark:text-green-500"> Username available!</p> -->
    </div>
    <div class="mb-5">
      <label for="username-success" class="block mb-2 text-sm font-medium text-slate-600 ">Content</label>
        <input type="text" name="content" class="block w-full p-4 bg-gray-50 border border-blue-500  text-green-900  text-sm rounded-lg focus:ring-blue-700 focus:border-green-500 block w-full p-2.5  ">
    </div>
    <div class="mb-5">
        <label class="block mb-2 text-sm font-medium text-slate-600" for="user_avatar">Upload image </label>
        <input name="file" class="block w-full text-sm bg-gray-50 border border-blue-500  text-green-900 focus:outline-none " aria-describedby="user_avatar_help" id="user_avatar" type="file" style="border-radius: 8px;">
    </div>
    <div class="mt-6">
        <button type="button" class="text-white bg-sky-900 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 ">Submit</button>
    </div>


    
  </form>
  
</div>


</layout:layout>