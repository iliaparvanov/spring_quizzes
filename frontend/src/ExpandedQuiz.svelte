<script>
	import {onMount, afterUpdate, onDestroy} from "svelte";

	export let quiz;

	let checkedAnswers = [];
</script>

{#if quiz === null || quiz === undefined}
<p>
	No quiz selected!
</p>
{:else}
<div id={`quiz-${quiz.id}`}>
	<p>
		Taking {quiz.name}
	</p>
</div>
{#each quiz.content as question}
	<p>
		{question.text}
	</p>
	{#each question.answers as answer}
		<li>{answer.text} <input type=checkbox bind:group={checkedAnswers} value={answer.id}></li>
	{/each}


	{#each checkedAnswers as a}
	{a}
	{/each}
{/each}
{/if}
