<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	$(document).ready(function(){
		$(".nav li.forecast").addClass("active");
		
		// Retreving user's company's forecast.
		var forecast = new Forecast();
		forecast.fetch({async : false});
		new ForecastView({model : forecast});
	});
</script>

<div class="row forecast-container">

</div>

<div class="row">
	<button class="save btn btn-primary">Save</button>
</div>

<script type="text/template" id="forecast-template">
	<h3>Treasury Plan for <@= forecast.company.name @></h3>
	
	<input type="hidden" class="id" value="<@= forecast.id @>" />	
	<input type="hidden" class="company" value="<@= forecast.company.id @>" />

	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label">Start date</label>
			<div class="col-sm-6">
      			<input type="text" class="form-control startDate" id="inputEmail3" placeholder="Start date" value="<@= forecast.startDate @>" />
    		</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">End date</label>
			<div class="col-sm-6">
      			<input type="text" class="form-control endDate" id="inputEmail3" placeholder="End date" value="<@= forecast.endDate @>" />
    		</div>
		</div>
	</div>

	<!-- Scenarios Nav tabs -->
	<div class="scenarios">
		<h4>Scenarios</h4>

		<ul class="nav nav-tabs">
			<li><a class="addScenario">+</a></li>
		</ul>

		<!-- Scenarios Tab panes -->
		<div class="tab-content">

		</div>
	</div>

	<div class="fixedJournalEntries form-group">
		<h4>Fixed journal entries</h4>
		<button class="btn btn-primary btn-xs addEntry">Add</button>
		<div class="none form-inline">None</div>
	</div>

	<div class="recurrentJournalEntries form-group">
		<h4>Recurrent journal entries</h4>
		<button class="btn btn-primary btn-xs addEntry">Add</button>
		<div class="none form-inline">None</div>
	</div>
</script>

<script type="text/template" id="scenarioNav-template">
<@	var scenarioId = scenario.id;
	var newScenario = false;

	if(scenarioId == 0){
		newScenario = true;
		scenarioId = "new-" + ($(".scenarios .tab-pane[data-newScenario='true']").size() + 1);
	}	@>

	<li class="scenarioNav" data-scenario-id="<@= scenarioId @>"><a href="#scenario-<@= scenarioId @>" data-toggle="tab"><@= scenario.label @></a></li>
</script>

<script type="text/template" id="scenarioTab-template">
<@	var scenarioId = scenario.id;
	var newScenario = false;

	if(scenarioId == 0){
		newScenario = true;
		scenarioId = "new-" + ($(".scenarios .tab-pane[data-newScenario='true']").size() + 1);
	}	@>

	<div class="tab-pane" id="scenario-<@= scenarioId @>" data-scenario-id="<@= scenarioId @>" data-newScenario="<@= newScenario @>">
		<div class="form-inline">
			<div class="form-group">
				<label class="col-sm-2 control-label">Label</label>
				<div class="col-sm-6">
					<input type="text" class="form-control scenarioLabel" placeholder="Label" value="<@= scenario.label @>" />
				</div>
			</div>
		</div>
	</div>
</script>

<script type="text/template" id="fixedJournalEntry-template">
	<div class="journalEntry form-inline" data-journalEntry-id="<@= journalEntry.id @>">
		<div class="form-group">
			<input type="text" class="form-control entryLabel" placeholder="Label" value="<@= journalEntry.label @>" />
		</div>
		<div class="form-group">
			<input type="number" class="form-control entryAmount" placeholder="Amount" step="0.01" value="<@= journalEntry.amount @>" />
		</div>
		<div class="form-group">
			<input type="text" class="form-control entryDate" placeholder="Date" value="<@= journalEntry.timestamp @>" />
		</div>
		<div class="form-group">
			<button class="remove btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span></button>
		</div>
	</div>
</script>

<script type="text/template" id="recurrentJournalEntry-template">
	<div class="recurrentJournalEntry" data-entry-id="<@= journalEntry.id @>">
		<div class="form-inline">
			<div class="form-group"><input type="text" class="form-control entryLabel" placeholder="Label" value="<@= journalEntry.label @>" /></div>
		</div>

		<div class="journalEntryParams">
			<div class="form-inline">
				<div class="form-group">
					<input type="number" class="form-control entryAmount" placeholder="Amount" step="0.01" value="<@= journalEntry.amount @>" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control entryDate" placeholder="Date" value="<@= journalEntry.timestamp @>" />
				</div>
			</div>

			<div class="form-inline reccurence">
				<div class="recurrenceUnit form-group" data-recurrence-type="weekDays">
					<div>Week Days</div>
					<div>
						<select multiple="multiple">
				<@	for(var i = 1; i <= 7; i++){	@>
							<option value="<@= i @>"
						<@	if(indexOf(journalEntry.recurrence.weekDays, i) > -1){	@>
								selected="selected" 
						<@	}	@>
							><@= i @></option>
				<@	}	@>
						</select>
					</div>
				</div>

				<div class="recurrenceUnit form-group" data-recurrence-type="monthDays">
					<div>Month Days</div>
					<div>
						<select multiple="multiple">
				<@	for(var i = 1; i <= 31; i++){	@>
							<option value="<@= i @>"
						<@	if(indexOf(journalEntry.recurrence.monthDays, i) > -1){	@>
								selected="selected" 
						<@	}	@>
							><@= i @></option>
				<@	}	@>
						</select>
					</div>
				</div>

				<div class="recurrenceUnit form-group" data-recurrence-type="yearDays">
					<div>Year Days</div>
					<div>
						<select multiple="multiple">
				<@	for(var i = 1; i <= 365; i++){	@>
							<option value="<@= i @>"
						<@	if(indexOf(journalEntry.recurrence.yearDays, i) > -1){	@>
								selected="selected" 
						<@	}	@>
							><@= i @></option>
				<@	}	@>
						</select>
					</div>
				</div>

				<div class="recurrenceUnit form-group" data-recurrence-type="weeks">
					<div>Weeks</div>
					<div>
						<select multiple="multiple">
				<@	for(var i = 1; i <= 42; i++){	@>
							<option value="<@= i @>"
						<@	if(indexOf(journalEntry.recurrence.weeks, i) > -1){	@>
								selected="selected" 
						<@	}	@>
							><@= i @></option>
				<@	}	@>
						</select>
					</div>
				</div>

				<div class="recurrenceUnit form-group" data-recurrence-type="months">
					<div>Months</div>
					<div>
						<select multiple="multiple">
				<@	for(var i = 1; i <= 12; i++){	@>
							<option value="<@= i @>"
						<@	if(indexOf(journalEntry.recurrence.months, i) > -1){	@>
								selected="selected" 
						<@	}	@>
							><@= i @></option>
				<@	}	@>
						</select>
					</div>
				</div>

				<div class="recurrenceUnit form-group" data-recurrence-type="years">
					<div>Years</div>
					<div>
						<select multiple="multiple">
				<@	for(var i = 2000; i <= 2030; i++){	@>
							<option value="<@= i @>"
						<@	if(indexOf(journalEntry.recurrence.years, i) > -1){	@>
								selected="selected" 
						<@	}	@>
							><@= i @></option>
				<@	}	@>
						</select>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</script>