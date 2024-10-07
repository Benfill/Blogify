<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>


<layout:layout title="Home">

<section x-data="{ slideWidth: 0, activeSlide: 0, slideCount: 3 }" class="relative py-20 md:py-32 overflow-hidden">
  <img class="absolute top-0 left-0 mt-4 -ml-20 md:-ml-0" src="saturn-assets/images/content/stars-left-top.svg" alt="">
  <div class="relative container px-4 mx-auto">
    <div class="text-center mb-18">
      <span class="inline-block py-1 px-3 mb-4 text-xs font-semibold text-orange-900 bg-orange-50 rounded-full">SOCIAL MEDIA</span>
      <h1 class="font-heading text-4xl xs:text-6xl md:text-7xl font-bold text-gray-900 mb-4">
        <span>Follow us on</span>
        <span class="font-serif italic">instagram</span>
      </h1>
      <p class="text-gray-500">Risus viverra justo sagittis vestibulum metus.</p>

    <form class="max-w-md my-4 mx-auto">   
      <label for="default-search" class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search</label>
      <div class="relative">
          <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
              <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
              </svg>
          </div>
          <input type="search" id="default-search" class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg  focus:ring-blue-500 focus:border-blue-500 " placeholder="Search by title..." required />
          <button type="submit" class="text-white absolute end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Search</button>
      </div>
    </form>

    </div>
    <div class="relative max-w-sm sm:max-w-7xl mx-auto">
     
      <div class="px-6">
        <div class="overflow-hidden">
          <div :style="'transform: translateX(-' + slideWidth + 'px)'" class="flex transition-transform duration-500 ease-in-out -m-5" style="transform: translateX(-0px)">
            <div x-ref="slide1" class="flex-shrink-0 w-full p-5">
              <div class="flex flex-wrap -mx-4">
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="w-full md:w-1/3 lg:w-1/4 px-4">
                  <a class="group block max-w-sm mx-auto md:max-w-none h-full border border-gray-100 bg-white rounded-xl transform hover:scale-105 transition duration-500" href="#">
                    <div class="flex items-center justify-between px-4 py-5">
                      <span class="text-sm">@saturn_ui</span>
                      <img src="saturn-assets/images/instagram-photos/icon-instagram.svg" alt="">
                    </div>
                    <div class="h-72">
                      <img class="block w-full h-full" src="saturn-assets/images/instagram-photos/photos-insta-color3.png" alt="">
                    </div>
                    <div class="px-4 pt-4 pb-5">
                      <span class="block text-sm tetx-gray-800 mb-2.5">This is example post</span>
                      <div class="flex items-center">
                        <img class="mr-1.5" src="saturn-assets/images/instagram-photos/heart-icon.svg" alt="">
                        <span class="text-sm text-gray-500">12.903</span>
                      </div>
                    </div>
                  </a>
                </div>
               
             </div>

            <!-- Pagination -->
            <nav aria-label="Page navigation example" class="flex align-center justify-center m-2">
              <ul class="inline-flex -space-x-px text-base h-10">
                <li>
                  <a href="#" class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">Previous</a>
                </li>
                <li>
                  <a href="#" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">1</a>
                </li>
                <li>
                  <a href="#" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">2</a>
                </li>
                <li>
                  <a href="#" aria-current="page" class="flex items-center justify-center px-4 h-10 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white">3</a>
                </li>
                <li>
                  <a href="#" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">4</a>
                </li>
                <li>
                  <a href="#" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">5</a>
                </li>
                <li>
                  <a href="#" class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">Next</a>
                </li>
              </ul>
            </nav>

            <!-- Pagination -->

         
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


</layout:layout>