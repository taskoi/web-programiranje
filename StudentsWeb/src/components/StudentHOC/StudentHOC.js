import React from 'react';
import StudentList from '../StudentList/StudentList';
import NewStudent from '../NewStudent/NewStudent';
import EditStudent from '../EditStudent/EditStudent';

export default class StudentHOC extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            // students: listStudents(),
            students: [],
            isActiveEditStudent: false,
            studentIndex: 0
        };

        this.handler = this.handler.bind(this);
        this.handlerRemove = this.handlerRemove.bind(this);
        this.handlerForUpdate = this.handlerForUpdate.bind(this);
        this.handlerForNewStudent = this.handlerForNewStudent.bind(this);
        this.handleNewData = this.handleNewData.bind(this);

    }

    componentDidMount() {
        document.title = "Student management";
        fetch(`http://localhost:8080/students`, { mode: 'cors' }).then(response => response.json()).then(students => this.setState({ students }));
    }

    handlerRemove(id) {
        fetch(`http://localhost:8080/students/${id}`, {
            method: 'DELETE'
        }).then(newStudent => {
            this.setState(
                () => {
                    return {
                        students: this.state.students.filter(student => student.index !== id)
                    }
                }
            );
        });
    }
    handlerForUpdate(data) {
        console.log(data);
        fetch(`http://localhost:8080/students/${data.index}`, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => {
            return response.json();
        }).then(newStudent => {
            this.setState(
                () => {
                    return {
                        students: [...this.state.students.filter(student => student.index !== newStudent.index), newStudent]
                    }
                }
            );
        });
    }

    handleNewData(data) {
        fetch('http://localhost:8080/students', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => {
            return response.json();
        }).then(newStudent => {
            this.setState(
                () => {
                    return {
                        students: [...this.state.students, newStudent]
                    }
                }
            );
        });

    }

    handlerForNewStudent(status) {
        console.log(status);
        this.setState(
            () => {
                return {
                    isActiveNewStudent: status
                }
            }
        );
    }

    handler(id, status) {

        this.setState(
            () => {
                return {
                    isActiveEditStudent: status,
                    studentIndex: id
                }
            }
        )
        console.log(this.state)
    }

    selectStudentByIndex = (studentIndex) => {
        return this.state.students.find(student => {
            return student.index === studentIndex;
        });
    };

    render() {
        let editComponent = () => {
            if (this.state.isActiveEditStudent) {
                return <EditStudent student={this.selectStudentByIndex(this.state.studentIndex)}
                    handlerForUpdate={this.handlerForUpdate} />
            } else {
                return null;
            }
        };
        let newComponent = () => {
            if (this.state.isActiveNewStudent) {
                return <NewStudent handleNewData={this.handleNewData} />;
            } else {
                return null;
            }
        };
        return (
            <div className="container-fluid">

                <div className='row'>
                    <div className="col-4">
                        <StudentList students={this.state.students} handlerForNewStudent={this.handlerForNewStudent}
                            handler={this.handler} handlerRemove={this.handlerRemove} />

                    </div>

                    <div className="row">

                    </div>
                    <div className="col-8">
                        {editComponent()}
                        {newComponent()}
                    </div>
                </div>

            </div>
        );

    }
}