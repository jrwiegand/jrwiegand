<script lang="ts">
	import BigRedButton from './BigRedButton.svelte';

	let horn = '/horn.mp3';

	const audio = new Audio();
	audio.src = horn;

	function handleClick() {
		audio.play();
	}
</script>

<BigRedButton on:click={handleClick} />
