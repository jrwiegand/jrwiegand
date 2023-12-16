<script>
	export let data;
</script>

<main>
	<h1>todos</h1>

	<form method="POST" action="?/create">
		<label>
			add a todo:
			<input
				name="description"
				autocomplete="off"
			/>
		</label>
	</form>

	<ul>
		{#each data.todos as todo (todo.id)}
			<li>
				<form method="POST" action="?/delete">
					<input type="hidden" name="id" value="{todo.id}" />
					<span>{todo.description}</span>
					<button aria-label="Mark as Complete" />
				</form>
			</li>
		{/each}
	</ul>
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
		padding: 0 1em;
	}

	input {
		width: 12em;
	}

	span {
		flex: 1;
	}

	ul {
		padding: 0 1em;
	}

	button {
		border: none;
		background: url(./remove.svg) no-repeat 50% 50%;
		background-size: 1rem 1rem;
		cursor: pointer;
		aspect-ratio: 1;
		height: 100%;
		opacity: 0.5;
		transition: opacity 0.2s;
	}

	button:hover {
		opacity: 1;
	}
</style>
