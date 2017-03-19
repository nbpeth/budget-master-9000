import store from './expensesStore.js';
import { createExpenseAction } from './actions.js';

class CreateExpenseForm extends React.Component {
	constructor(props){
        super(props);
    }

	createExpense(data){
		store.dispatch(createExpenseAction(data));
	}
 
    render(){
		const getFormData = () => {
			var location = document.getElementById("location").value;
			var cost = document.getElementById("cost").value;
			var expenseType = document.getElementById("type").value;
			var dayOfWeek = document.getElementById("day").value;
			var description = document.getElementById("description").value;

			return {
				location: location,
				cost: cost,
				expenseType: expenseType,
				dayOfWeek: dayOfWeek, 
				description: description, 
				expenseDate: new Date()
			}
		}
        return(
            <div>
				<h3>Create</h3>
					<form>
			  			<div className="form-group">
			  				<input type="email" className="form-control" id="location" aria-describedby="emailHelp" placeholder="Location" s/><br/>
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
			  				<select className="form-control" id="day">
								<option>Monday</option>
								<option>Tuesday</option>
								<option>Wednesday</option>
								<option>Thursday</option>
								<option>Friday</option>
								<option>Saturday</option>
								<option>Sunday</option>
		    				</select><br/>
			  				<input type="text" className="form-control" id="description" placeholder="Description"/><p/>

		    				<button type="button" className="btn btn-primary" onClick={ () => this.createExpense(getFormData())} >Submit</button>
			  			</div>
		  			</form>
			</div>
        );
    }
}

export default CreateExpenseForm;