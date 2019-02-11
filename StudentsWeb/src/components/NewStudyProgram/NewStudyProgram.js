import React from 'react';

export default class NewStudyProgram extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            name: ""
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
    }

    handleNameChange = (event) => {
        this.setState({
            name : event.target.value
        });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        
        const data = {
            name:event.target.value
        };

        this.props.handleNewData(data);
    }
    render(){
        return (
            <div className="card">
            <div className="card-header">
                <h4>New Study Program</h4>
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