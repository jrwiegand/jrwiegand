<script>
	import AudioPlayer, { stopAll } from './AudioPlayer.svelte';
	import { tracks } from './tracks.js';
</script>

<div class="centered">
	{#each tracks as track}
		<AudioPlayer {...track} />
	{/each}
</div>

<button on:click={stopAll}>stop all</button>

<style>
	.centered {
		display: flex;
		flex-direction: column;
		height: 100%;
		justify-content: center;
		gap: 0.5em;
		max-width: 40em;
		margin: 0 auto;
	}
</style>
