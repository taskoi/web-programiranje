import React from 'react';

export default class EditStudyProgram extends React.Component {
    constructor(props) {
        super(props);
        this.state = this.props.studyProgram;

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit = (event) => {
        event.preventDefault();

        const data = {
            id: event.target.id,
            name: event.target.name
        };
        
        console.log("vleze ovde");
        this.props.handlerForUpdate(data);

    }
    handleNameChange = (event) => {
        this.setState({
            name: event.target.value
        }) 
    }

    
    render() {
        return (
            <div className="card">
                <div className="card-header">
                    <h4>Edit Study PRogram</h4>
                </div>

                <div className="card-body">
                    <form action="#" onSubmit={this.handleSubmit.bind(this)}>
                        <div className="form-group">
                            <label htmlFor="name">Name:</label>
                            <input type="text" id="name" className="form-control" value={this.state.name} onChange={this.handleNameChange} />
                        </div>

                        <div className="row pb-1">
                            <button className="btn btn-success">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}