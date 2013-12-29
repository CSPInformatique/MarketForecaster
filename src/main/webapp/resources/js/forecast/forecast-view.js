window.ForecastView = Backbone.View.extend({
    el : ".forecast-container",

	initialize : function() {
	    this.template = _.template($("#forecast-template").html());
	    
	    /*--- binding ---*/
	    _.bindAll(this, "addRecurrentJournalEntry");
	    _.bindAll(this, "render");
	    this.model.bind("change", this.render);
	    /*---------------*/
	    
	    this.render();
	},
	
	addFixedJournalEntry : function(){
		this.renderFixedJournalEntry({id: 0, asset : null, label : "", amount : 0});
	},

	addRecurrentJournalEntry : function(){
		this.renderRecurrentJournalEntry({
			id: 0, 
			asset : null, 
			label : "", 
			amount : "", 
			recurrence : {
				id : 0,
				weekDays : [],
				monthDays : [],
				yearDays : [],
				weeks : [],
				months : [],
				years : []
			}
		});
	},
	
	removeScenario : function(scenarioId){
		if(scenarioId == 0){
			this.scenarioAdded = false;
		}
		
		$scenarios = $(".forecast-container .scenarios-container");
		
		// Remove tab header.
		$scenarios.find(".nav li[data-scenario-id='" + scenarioId + "']").remove();
		
		// Remove tab content.
		$scenarios.find(".tab-content li[data-scenario-id='" + scenarioId + "']").remove();
	},
	
	render : function(){
		var forecast = this.model.toJSON();
		var renderedContent = this.template({forecast : forecast});

		var $element = $(".forecast-container");
		
		$element.html(renderedContent);
		
		for(var index in forecast.fixedJournalEntries){
			this.renderFixedJournalEntry(forecast.fixedJournalEntries[index]);
		}
		
		for(var index in forecast.recurrentJournalEntries){
			this.renderRecurrentJournalEntry(forecast.recurrentJournalEntries[index]);
		}
		
		var forecastView = this;
		$(".fixedJournalEntries button.addEntry").click(function(){
			forecastView.addFixedJournalEntry();
		});

		$(".recurrentJournalEntries button.addEntry").click(function(){
			forecastView.addRecurrentJournalEntry();
		});
		
		$element.find(".startDate, .endDate").each(function(index, dateElement){
			$dateElement = $(this);
			
			$dateElement.val(
				moment(parseInt($dateElement.val())).format("YYYY-MM-DD")
			).datepicker({format : "yyyy-mm-dd"});
		});
		
		$element.find(".addScenario").click(function(){
			forecastView.renderScenario({
				id : 0,
				label : "New scenario"
			});
			
			$(".scenarios .scenarioNav a").last().click();
		});
		
//		$element.find("select").select2();
		
		for(var index in forecast.scenarios){
			this.renderScenario(forecast.scenarios[index]);
		}
		
		$(".scenarios .scenarioNav a").first().click();
		
		$("button.save").click(function(){
			forecastView.saveForecast();
		});
	},
	
	renderJournalEntry : function(journalEntry, $journalEntryTemplate, $journalEntries){
		var $none = $journalEntries.find(".none");
		$journalEntries.append(
			_.template($journalEntryTemplate.html())({journalEntry : journalEntry})
		);
		
		$none.hide(); 
		
		$journalEntries.find("button.remove").last().click(function(){
			$(this).closest(".journalEntry").remove();
			
			if($journalEntries.find(".journalEntry").size() == 0){
				$none.show();
			}
		});
		
		$dateInput = $journalEntries.find("input.entryDate").last();
		$dateInput.val(moment(parseInt($dateInput.val())).format("YYYY-MM-DD")).datepicker({format : "yyyy-mm-dd"});
		
		$journalEntries.find("select").select2({width: 'element'});
	},
	
	renderFixedJournalEntry : function(fixedJournalEntry){
		this.renderJournalEntry(fixedJournalEntry, $("#fixedJournalEntry-template"), $(".fixedJournalEntries"));
	},

	renderRecurrentJournalEntry : function(recurrentJournalEntry){
		this.renderJournalEntry(recurrentJournalEntry, $("#recurrentJournalEntry-template"), $(".recurrentJournalEntries"));
	},
	
	renderScenario : function(scenario){
		$addScenarioEl = $(".scenarios .addScenario").closest("li"); 
		
		$addScenarioEl.before(
			_.template($("#scenarioNav-template").html())({scenario : scenario})
		);
		
		$(".scenarios .tab-content").append(
			_.template($("#scenarioTab-template").html())({scenario : scenario})
		);
		
		$tabContent = $(".scenarios .tab-content .tab-pane").last().find("input.scenarioLabel").keyup(function(){
			$(".scenarioNav a").last().html($(this).val());
		});
	},
	
	saveForecast : function(){
		/*
		Set<RecurrentJournalEntry> recurrentJournalEntries,
		Set<Scenario> scenarios
		 */
		
		var $element = $(".forecast-container");
		
		var forecastId = parseInt($element.find("input.id").val());
		
		var scenarios = [];
		$(".forecast-container .tab-content .tab-pane").each(function(index, element){
			$tabElement = $(this);
			
			var id = $tabElement.attr("data-scenario-id");
			if($tabElement.attr("data-newScenario") == "true"){
				id = 0;
			}
			
			scenarios.push({
				id : id,
				label : $tabElement.find("input.scenarioLabel").val()
			});
		});
		
		// Retreiving all fixed journal entries configured.
		var fixedJournalEntries = [];
		$(".forecast-container .fixedJournalEntry").each(function(index, element){
			var $entryElement = $(element);
			fixedJournalEntries.push({
				id : $entryElement.attr("data-journalEntry-id"),
				label : $entryElement.find("input.entryLabel").val(),
				amount : $entryElement.find("input.entryAmount").val(),
				timestamp : +moment($entryElement.find("input.entryDate").val(), "YYYY-MM-DD"),
			});
		});
		
		// Retreiving all recurrent journal entries configured.
		var recurrentJournalEntries = [];
		$(".forecast-container .recurrentJournalEntry").each(function(index, entryElement){
			var $entryElement = $(entryElement);
			
			var recurrence  = {};
			$entryElement.find(".recurrenceUnit").each(function(recurrenceIndex, recurrenceElement){
				var $recurrenceElement = $(recurrenceElement);
				var value = $recurrenceElement.find("select").val();
				
				if(value != null){
					recurrence[$recurrenceElement.attr("data-recurrence-type")] = value;
				}
			});
			
			recurrentJournalEntries.push({
				id : $entryElement.attr("data-journalEntry-id"),
				label : $entryElement.find("input.entryLabel").val(),
				amount : $entryElement.find("input.entryAmount").val(),
				recurrence : recurrence,
				startDate : +moment($entryElement.find("input.entryDate").val(), "YYYY-MM-DD"),
			});
		});
		
		new Forecast({
			id : forecastId,
			company : {
				id : $element.find("input.company").val()
			},
			fixedJournalEntries : fixedJournalEntries,
			recurrentJournalEntries : recurrentJournalEntries,
			scenarios : scenarios,
			startDate : +moment($element.find(".startDate").val(), "YYYY-MM-DD"),
			endDate : +moment($element.find(".endDate").val(), "YYYY-MM-DD")
		}).save({}, {async : false});
	} 
});