<script lang="ts">
	let selection: Selection | null = null;

	const handleSelectionChange = () => selection = document.getSelection();
</script>

<svelte:document on:selectionchange={handleSelectionChange}/>

<h1>Select this text to fire events</h1>
<p>Selection: {selection ? selection.toString() : ''}</p>
