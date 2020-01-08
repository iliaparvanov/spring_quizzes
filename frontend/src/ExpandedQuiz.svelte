<script>
	import {onMount, afterUpdate, onDestroy} from "svelte";

	export let quiz;

	let checkedAnswers = [];

	let submitQuiz = async () => {
		let submission = {
			"id": 0,
			"scored_points": 0,
			"total_points": 0,
			"quiz_id": 1,
			"answers": [
				{
					"id": 1,
					"text": "fdefe"
				},
				{
					"id": 2,
					"text": "fefe"
				}
			]
		}
	}
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
	<button on:click{submitQuiz}>Submit</button>
{/each}
{/if}
