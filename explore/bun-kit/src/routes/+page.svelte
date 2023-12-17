<script>
	import { fly, slide } from 'svelte/transition';
	import { enhance } from '$app/forms';

	export let data;
	export let form;

	let creating = false;
	let deleting = [];
</script>

<main>
	<h1>todos</h1>

	{#if form?.error}
		<p class="error">{form.error}</p>
	{/if}

	<form
		method="POST"
		action="?/create"
		use:enhance={() => {
			creating = true;

			return async ({ update }) => {
				await update();
				creating = false;
			};
		}}
	>
		<label>
			add a todo:
			<input
				disabled={creating}
				name="description"
				value="{form?.description ?? ''}"
				autocomplete="off"
				required
			/>
		</label>
	</form>

	<ul>
		{#each data.todos.filter((todo) => !deleting.includes(todo.id)) as todo (todo.id)}
			<li in:fly={{ x: 400 }} out:slide={{ duration: 400, axis: 'x' }}>
				<form
					method="POST"
					action="?/delete"
					use:enhance={() => {
						deleting = [...deleting, todo.id];
						return async ({ update }) => {
							await update();
							deleting = deleting.filter((id) => id !== todo.id);
						};
					}}
				>
					<input type="hidden" name="id" value="{todo.id}" />
					<span>{todo.description}</span>
					<button aria-label="Mark as Complete" />
				</form>
			</li>
		{/each}
	</ul>

	{#if creating}
		<span class="saving">saving...</span>
	{/if}
</main>

<style>
	main {
		max-width: 20em;
		margin: 0 auto;
	}

	form {
		display: flex;
		align-items: center;
		height: 3em;
	}

	label {
		display: flex;
		align-items: center;
		justify-content: space-between;
		width: 100%;
	}

	input {
		width: 12em;
	}

	span {
		flex: 1;
	}

	ul {
		padding: 0 0 0 1em;
	}

	button {
		border: none;
		background: url(./remove.svg) no-repeat 50% 50%;
		background-size: 1rem 1rem;
		cursor: pointer;
		aspect-ratio: 1;
		height: 100%;
		width: 1em;
		opacity: 0.5;
		transition: opacity 0.2s;
		padding: 0;
	}

	button:hover {
		opacity: 1;
	}

	.error {
		color: red;
	}
</style>
