// import React from 'react';
import React from 'react';
import Todo from './Todo';
import AddTodo from './AddTodo';
import './App.css';
import { Paper, List, Container } from "@material-ui/core";
import { call } from './service/ApiService';
// import logo from './logo.svg';
// import { render } from '@testing-library/react';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
    };
  }

  // (1) 함수 추가
  add = (item) => {
    console.log("item.title.length : ", item.title.length);
    const thisItems = this.state.items;
    item.id = "ID-" + thisItems.length; // key를 위한 id 추가
    item.done = false; // done 초기화
    thisItems.push(item); // 리스트에 아이템 추가
    this.setState({items: thisItems}); // 업데이트는 반드시 this.setState로 해야 됨
    console.log("items : ", this.state.items);
  }

  delete = (item) => {
    const thisItems = this.state.items;
    console.log("Before Update Items : ", this.state.items);
    const newItems = thisItems.filter(e => e.id !== item.id);
    this.setState({items: newItems}, () => {
      // 디버깅 콜백
      console.log("Update Items : ", this.state.items)
    });

  }

  componentDidMount() {

    call("/todo", "GET", null).then((response) => {
      this.setState({items: response.data})
    });

  }

  add = (item) => {
    call("/todo", "POST", item).then((response) => {
      this.setState({items: response.data})
    });
  };

  delete = (item) => {
    call("/todo", "DELETE", item).then((response) => {
      this.setState({items: response.data})
    });
  };

  update = (item) => {
    call("/todo", "PUT", item).then((response) =>
      this.setState({items: response.data})
    );
  }

  render() {

    var todoItems = this.state.items.length > 0 && (
      <Paper style={{margin: 16}}>
        <List>
          {this.state.items.map((item, idx) => (
            <Todo item={item} key={item.id} delete={this.delete} update={this.update}/>
          ))}
        </List>
      </Paper>
    )

    return (
      <div className="App">
        <Container maxWidth="md">
          <AddTodo add={this.add} />
          <div className="TodoList">{todoItems}</div>
        </Container>
      </div>

    )
  }
}

// function App() {
//   const items = [
//     {id: 0, title: "Hello World 1", done: true},
//     {id:1, title: "Hello World 2", done: false}
//   ];

//   var todoItems = items.map((item, idx) =>(
//     <Todo item={item} key={item.id} />
//   ));
//   return (

//     <div className="App">
//       {todoItems}
//     </div>
//   )

// }


export default App;
