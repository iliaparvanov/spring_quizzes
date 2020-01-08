<script>
	import {onMount, afterUpdate, onDestroy} from "svelte";

	export let quiz;

	let checkedAnswers = [];
	let quizIsDone = false;
	let createdSubmission;

	let submitQuizWrapper = async () => {
		let submissionAnswers = checkedAnswers.map((n) => {
			return {
				"id": n,
				"text": ""
			}
		});

		let submission = {
			"id": 0,
			"scored_points": 0,
			"total_points": 0,
			"quiz_id": quiz.id,
			"answers": submissionAnswers
		};

		let submissionLocation;
		// POST submission
		await fetch('http://localhost:8080/api/v1/submissions', {
			method: 'POST', // or 'PUT',
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Headers': 'Location'
			},
			body: JSON.stringify(submission),
		}).then((response) => {
			submissionLocation = response.headers.get('Location');
		})
		.catch((error) => { console.log(error); });

		let response = await fetch(submissionLocation);
		return await response.json();
	}

	let submitQuiz = async () => {
		createdSubmission = submitQuizWrapper();
		quizIsDone = true;
	}
</script>

{#if quizIsDone === true} 
	{#await createdSubmission}
		<p>Submitting quiz...</p>
	{:then data}
		<p>Score for {quiz.name}: {data.scored_points}/{data.total_points}</p>
	{:catch error}
		<p>Oops! There was an error: {error}</p>
	{/await}
	
{:else}
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
		{/each}
		<br>
		<button on:click={submitQuiz}>Submit</button>
	{/if}
{/if}