<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags"%>


<layout:layout title="Home">


	<div class="flex flex-col justify-center">
		<div class="pt-10">
			<h1 class="text-center text-xl">Comments List</h1>
		</div>


		<div class="relative overflow-x-auto py-20 px-10">
			<form class="max-w-sm mx-auto flex justify-end">
				<label for="status_select" class="sr-only">Status select</label> <select
					id="status_select" onChange="handleStatusChange()"
					class="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none dark:text-gray-400 dark:border-gray-700 focus:outline-none focus:ring-0 focus:border-gray-200 peer">
					<option selected disabled>Select Status</option>
					<option value="all">All</option>
					<option value="approved">Approved</option>
					<option value="denied">Denied</option>
				</select>
			</form>
			<table
				class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
				<thead
					class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
					<tr>
						<th scope="col" class="px-6 py-3">#ID</th>
						<th scope="col" class="px-6 py-3">Content</th>
						<th scope="col" class="px-6 py-3">Article</th>
						<th scope="col" class="px-6 py-3">Creation Date</th>
						<th scope="col" class="px-6 py-3">Status</th>
						<th scope="col" class="px-6 py-3">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comment" items="${ comments }">
						<tr
							class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
							<th scope="row"
								class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
								Id</th>
							<td class="px-6 py-4">${ comment.content }</td>
							<td class="px-6 py-4"><a href="#">${ comment.article.title }</a></td>
							<td class="px-6 py-4">${ comment.formattedCreationDate }</td>
							<td class="px-6 py-4">${ comment.status }</td>
							<td class="px-6 py-4">
								<!-- Modal toggle -->
								<button data-modal-target="crud-modal-${ comment.id }"
									data-modal-toggle="crud-modal-${ comment.id }"
									class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
									type="button">Update Status</button>

							</td>
						</tr>
					</c:forEach>


				</tbody>
			</table>

			<!-- Pagination -->
			<nav aria-label="Page navigation example"
				class="flex align-center justify-center m-2">
				<ul class="inline-flex -space-x-px text-base h-10">
					<li><a href="#"
						class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">Previous</a>
					</li>
					<li><a href="#"
						class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">1</a>
					</li>
					<li><a href="#"
						class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">2</a>
					</li>
					<li><a href="#" aria-current="page"
						class="flex items-center justify-center px-4 h-10 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white">3</a>
					</li>
					<li><a href="#"
						class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">4</a>
					</li>
					<li><a href="#"
						class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">5</a>
					</li>
					<li><a href="#"
						class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">Next</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>

	<c:forEach var="comment" items="${ comments }">




		<!-- Main modal -->
		<div id="crud-modal-${ comment.id }" tabindex="-1" aria-hidden="true"
			class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
			<div class="relative p-4 w-full max-w-md max-h-full">
				<!-- Modal content -->
				<div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
					<!-- Modal header -->
					<div
						class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
						<h3 class="text-lg font-semibold text-gray-900 dark:text-white">
							Update Comment Status</h3>
						<button type="button"
							class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
							data-modal-toggle="crud-modal-${ comment.id }">
							<svg class="w-3 h-3" aria-hidden="true"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 14 14">
                        <path stroke="currentColor"
									stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
									d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                    </svg>
							<span class="sr-only">Close modal</span>
						</button>
					</div>
					<!-- Modal body -->
					<form class="p-4 md:p-5"
						action="${ pageContext.request.contextPath }/comment/status/update"
						method="post">
						<div class="grid gap-4 mb-4 grid-cols-2">
							<div class="col-span-4 sm:col-span-2">
								<input name="comment_id" value="${ comment.id }" type="hidden" />
								<label for="category"
									class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Status</label>
								<select id="status_select" name="status"
									class="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none dark:text-gray-400 dark:border-gray-700 focus:outline-none focus:ring-0 focus:border-gray-200 peer">
									<option value="" disabled>Select Status</option>
									<option value="APPROVED"
										<c:if test="${comment.status == 'APPROVED'}">selected</c:if>>Approved</option>
									<option value="DENIED"
										<c:if test="${comment.status == 'DENIED'}">selected</c:if>>Denied</option>
								</select>
							</div>
						</div>
						<button type="submit"
							class="text-white inline-flex items-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
							<svg class="me-1 -ms-1 w-5 h-5" fill="currentColor"
								viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
											<path fill-rule="evenodd"
									d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z"
									clip-rule="evenodd"></path></svg>
							Save
						</button>
					</form>
				</div>
			</div>
		</div>
	</c:forEach>




	<script>
		const statusSelect = document.getElementById("status_select");
		function handleStatusChange() {
			const status = statusSelect.value;
			if (status !== "all")
				window.location.href = "/blogify/comment/" + status;
			else
				window.location.href = "/blogify/comment/";
		}
	</script>
</layout:layout>