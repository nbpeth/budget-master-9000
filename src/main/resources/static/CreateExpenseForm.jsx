import React from 'react';
import store from './expensesStore.js';
import { createExpenseAction, loadStatsAction, loadDataAction } from './actions.js';
import moment from 'moment';
import DatePicker from 'react-datepicker';


class CreateExpenseForm extends React.Component {
	constructor(props){
        super(props);
		this.state = {startDate:moment()};
    }

	formElements() {
		return [
			document.getElementById("location"),
			document.getElementById("cost"),
			document.getElementById("type"),
			document.getElementById("datepicker"),
			document.getElementById("description")
		];
	}

	createExpense(data){
		store.dispatch(createExpenseAction(data));

		this.formElements().forEach((field)=>{
			field.value = null;
		});
	}

	componentDidMount(){
		this.setState({startDate:moment()});
	}

	handleDateChange(date){
		this.setState({startDate:date});
	}
 
    render(){
		const getFormData = () => {
			var location = document.getElementById("location").value;
			var cost = document.getElementById("cost").value;
			var expenseType = document.getElementById("type").value;
			var expenseDate = document.getElementById("datepicker").value;
			var description = document.getElementById("description").value;

			return {
				location: location,
				cost: cost,
				expenseType: expenseType,
				description: description, 
				expenseDate: expenseDate
			};
		};
        return(
            <div>
				<h3>Create</h3>
					<form>
			  			<div className="form-group">
			  				<input type="email" className="form-control" id="location" aria-describedby="emailHelp" placeholder="Location" /><br/>
			  				<input type="number" className="form-control" id="cost" placeholder="Cost"/><br/>
							  <select className="form-control" id="type">
								<option>Groceries</option>
								<option>Household Supplies</option>
								<option>Entertainment</option>
								<option>Restaurant</option>
								<option>Health and Fitness</option>
								<option>Clothing</option>
								<option>Miscellaneous</option>

		    				</select><br/>
							{/*<input type="date" className="form-control" id="datepicker" />*/}
							<DatePicker selected={this.state.startDate} className="form-control" onChange={this.handleDateChange.bind(this)} id="datepicker"/><p/>
			  				<input type="text" className="form-control" id="description" placeholder="Description"/><p/>

		    				<button type="button" className="btn btn-primary" onClick={ () => this.createExpense(getFormData())} >Submit</button>
			  			</div>
		  			</form>
			</div>
        );
    }
}

export default CreateExpenseForm;