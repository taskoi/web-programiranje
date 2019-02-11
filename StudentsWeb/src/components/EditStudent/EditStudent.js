import React from "react";


class EditStudent extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.props.student;

    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleLastNameChange = this.handleLastNameChange.bind(this);
    this.handleStudentIndexChange = this.handleStudentIndexChange.bind(this);

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      name: this.state.name,
      lastName: this.state.lastName,
      index: this.state.index,

    };

    this.props.handlerForUpdate(data);

  }

  handleNameChange = (event) => {
    this.setState({
      name: event.target.value
    })
  }
  handleLastNameChange = (event) => {
    this.setState({
      lastName: event.target.value
    })
  }

  handleStudentIndexChange = (event) => {
    this.setState({
      index: event.target.value
    })
  }



  render() {
    return (
      <div className="card">
        <div className="card-header">
          <h4>Edit Student</h4>
        </div>

        <div className="card-body">
          <form action="#" onSubmit={this.handleSubmit.bind(this)}>
            <div className="form-group">
              <label htmlFor="name">Name:</label>
              <input type="text" id="name" className="form-control" value={this.state.name} onChange={this.handleNameChange} />
            </div>
            <div className="form-group">
              <label htmlFor="lastName">Last Name:</label>
              <input type="text" id="lastName" className="form-control" value={this.state.lastName} onChange={this.handleLastNameChange} />
            </div>
            <div className="form-group">
              <label htmlFor="index">Student index:</label>
              <input type="text" id="index" className="form-control" value={this.state.index} onChange={this.handleStudentIndexChange} />
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

export default EditStudent;
