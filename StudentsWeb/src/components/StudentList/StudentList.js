import React from "react";
import Student from "../Student/Student";


class StudentList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      paging: {
          currentPage: 1,
          itemsPerPage: 4
      }
    }

  }
  handleOnNewStudentClick = () => {
    this.props.handlerForNewStudent(true);
  };
  selectStudent = (id, status) => {
    this.props.handler(id, status);
  };

  selectStudentRemove = (id) => {
    this.props.handlerRemove(id);
  };

  
  changePage = (e) => {
    const pageNumber = Number(e.target.id);
    this.setState((state) => {
      const paging = state.paging;
      paging.currentPage = pageNumber;
      return { 'paging': paging };
    });
  };

  render() {
    const paging = this.state.paging;
    const indexOfLast = paging.currentPage * paging.itemsPerPage;
    const indexOfFirst = indexOfLast - paging.itemsPerPage;
    const items = this.props.students.slice(indexOfFirst, indexOfLast);
    const numberOfPages = Math.ceil(this.props.students.length / paging.itemsPerPage);
    const pageNumbers = [];

    for (let i = 1; i <= numberOfPages; i++) {
      pageNumbers.push(i);
    }
    const listItems = items.map((student) => {
      return (
        <Student handler={this.selectStudent} student={student} key={student.index}
          handlerRemove={this.selectStudentRemove} />
      );
    });
    const pages = pageNumbers.map(number => {
      return (
        <li className="btn" onClick={this.changePage} key={number} id={number}>{number}</li>
      )
    });

    return (
      <div className="card">
        <div className="card-header">
          <div className="row">
            <div className="col-1 offset-2">
              <button className="text-light btn btn-primary"  onClick={this.handleOnNewStudentClick} > New Student</button>
            </div>
          </div>
        </div>
        <div className="card-body">

          {listItems}
          <ul className='paging '>{pages}</ul>
        </div>

      </div>)
  }

}

export default StudentList;
