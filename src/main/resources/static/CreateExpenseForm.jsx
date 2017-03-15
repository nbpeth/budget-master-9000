import submitExpense from './expenseService.js';

class CreateExpenseForm extends React.Component {

    render(){
        return(
            <div>
				<h3>Create</h3>
					<form>
			  			<div className="form-group">
			  				<input type="email" className="form-control" id="location" aria-describedby="emailHelp" placeholder="Location"/><br/>
			  				<input type="number" className="form-control" id="cost" placeholder="Cost"/><br/>
			  				<input type="text" className="form-control" id="type" placeholder="Expense Type"/><br/>
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

		    				<button type="button" className="btn btn-primary">Submit</button>
			  			</div>
		  			</form>
			</div>
        );
    }
}

export default CreateExpenseForm;