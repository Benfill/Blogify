<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags"%>

<dashboard:dashboard>

	<div class="flex flex-col justify-center pb-10">
		<div class="pt-6">
			<h1 class="text-center text-xl">Comments List</h1>
		</div>

		<div class="mt-4 px-20">
			<div class="flex flex-wrap -mx-6">
				<div class="w-full px-6 sm:w-1/2 xl:w-1/3">
					<div
						class="flex items-center px-5 py-6 bg-white rounded-md shadow-sm">
						<div class="p-3 bg-indigo-600 bg-opacity-75 rounded-full">
							<svg class="w-8 h-8 text-white" viewBox="0 0 28 30" fill="none"
								xmlns="http://www.w3.org/2000/svg">
							  <path
									d="M14 2C7.373 2 2 6.373 2 12c0 2.917 1.335 5.54 3.442 7.296C5.076 21.893 3.51 24.305 3.5 24.32a1 1 0 00.893 1.64c3.212-.37 5.514-1.258 6.884-1.958C11.808 24.296 12.887 24.5 14 24.5c6.627 0 12-4.373 12-10S20.627 2 14 2z"
									fill="currentColor" />
							  <path
									d="M8 13a2 2 0 100-4 2 2 0 000 4zM14 13a2 2 0 100-4 2 2 0 000 4zM20 13a2 2 0 100-4 2 2 0 000 4z"
									fill="currentColor" />
							</svg>
						</div>

						<div class="mx-5">
							<h4 class="text-2xl font-semibold text-gray-700">${total	}</h4>
							<div class="text-gray-500">Total Comments</div>
						</div>
					</div>
				</div>


			</div>
		</div>

		<div class="relative overflow-x-auto pb-20 pt-5 px-10">
			<form class=" flex justify-end">
				<label for="status_select" class="sr-only">Status select</label> <select
					id="status_select" onChange="handleStatusChange()"
					class="border-2 border-indigo-600 px-5 block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none focus:outline-none focus:ring-0 focus:border-gray-500 peer">
					<option selected disabled>Select Status</option>
					<option value="all">All</option>
					<option value="approved">Approved</option>
					<option value="denied">Denied</option>
					<option value="pending">Pending</option>
				</select>
			</form>
			<table class="w-full text-sm text-left rtl:text-right text-gray-700">
				<thead class="text-xs text-gray-700 uppercase bg-gray-100">
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
						<tr class="bg-white border-b">
							<th scope="row"
								class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${ comment.id }</th>
							<td class="px-6 py-4">${ comment.content }</td>
							<td class="px-6 py-4"><a href="#">${ comment.article.title }</a></td>
							<td class="px-6 py-4">${ comment.formattedCreationDate }</td>
							<td class="px-6 py-4">${ comment.status }</td>
							<td class="px-6 py-4">
								<button data-modal-target="crud-modal-${ comment.id }"
									data-modal-toggle="crud-modal-${ comment.id }"
									class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
									type="button">Update Status</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:if test="${ totalPages > 1 }">
				<nav aria-label="Page navigation example pb-20"
					class="flex align-center justify-center m-2">
					<ul class="inline-flex -space-x-px text-base h-10">
						<!-- Previous Page Link -->
						<c:if test="${ page > 1 }">
							<li><a href="?page=${page - 1}"
								class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700">
									Previous </a></li>
						</c:if>

						<!-- Page Numbers -->
						<c:forEach var="i" begin="1" end="${totalPages}">
							<li><a href="?page=${i}"
								class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 ${page == i ? 'font-bold' : ''}">
									${i} </a></li>
						</c:forEach>

						<!-- Next Page Link -->
						<c:if test="${ page < totalPages }">
							<li><a href="?page=${page + 1}"
								class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700">
									Next </a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>

		</div>
	</div>

	<c:forEach var="comment" items="${ comments }">
		<div id="crud-modal-${ comment.id }" tabindex="-1" aria-hidden="true"
			class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
			<div class="relative p-4 w-full max-w-md max-h-full">
				<div class="relative bg-white rounded-lg shadow">
					<div
						class="flex items-center justify-between p-4 md:p-5 border-b rounded-t">
						<h3 class="text-lg font-semibold text-gray-900">Update
							Comment Status</h3>
						<button type="button"
							class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center"
							data-modal-toggle="crud-modal-${ comment.id }">
							<svg class="w-3 h-3" aria-hidden="true"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 14 14">
								<path stroke="currentColor" stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
							</svg>
							<span class="sr-only">Close modal</span>
						</button>
					</div>
					<form class="p-4 md:p-5"
						action="${ pageContext.request.contextPath }/comment/status/update"
						method="post">
						<div class="grid gap-4 mb-4 grid-cols-2">
							<div class="col-span-4 sm:col-span-2">
								<input name="comment_id" value="${ comment.id }" type="hidden" />
								<label for="category"
									class="block mb-2 text-sm font-medium text-gray-900">Status</label>
								<select id="status_select" name="status"
									class="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none focus:outline-none focus:ring-0 focus:border-gray-500 peer">
									<option value="" disabled>Select Status</option>
									<option value="APPROVED"
										<c:if test="${comment.status == 'APPROVED'}">selected</c:if>>Approved</option>
									<option value="DENIED"
										<c:if test="${comment.status == 'DENIED'}">selected</c:if>>Denied</option>
								</select>
							</div>
						</div>
						<button type="submit"
							class="text-white inline-flex items-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
							<svg class="me-1 -ms-1 w-5 h-5" fill="currentColor"
								viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd"
									d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z"
									clip-rule="evenodd"></path>
							</svg>
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
</dashboard:dashboard>
