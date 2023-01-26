import React from 'react';
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton } from "@material-ui/core";
import { DeleteOutlined } from '@material-ui/icons';
// import { Delete } from '@material-ui/icons';

class Todo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {item: props.item, readOnly: true};
        this.delete = props.delete;
        this.update = props.update; // update를 this.update에 할당
    }

    // delete 함수 추가
    deleteEventHandler = () => {
        this.delete(this.state.item);
    };

    // readOnly off 함수 추가 (타이틀 클릭)
    offReadOnlyMode = () => {
        console.log("Event ! ", this.state.readOnly)
        this.setState({readOnly: false}, () => {
            console.log("ReadOnly ? " , this.state.readOnly)
        });
    };

    // readOnly on 함수 추가 (Enter 입력)
    enterKeyEventHandler = (e) => {
        if(e.key == 'Enter') {
            this.setState({readOnly: true});
            this.update(this.state.item); // 엔터를 누르면 저장
        }
    };

    // 타이틀 수정 함수
    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item: thisItem});
    }

    // update 함수 추가
    checkboxEventHandler = () => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({item: thisItem});
        this.update(this.state.item); // 체크박스가 변경되면 저장
    };

    render() {
        const item = this.state.item;
        return (
            <ListItem>
                <Checkbox checked={item.done} color="primary" disableRipple onChange={this.checkboxEventHandler}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label": "naked", readOnly: this.state.readOnly}}
                        onClick={this.offReadOnlyMode}
                        onKeyPress={this.enterKeyEventHandler}
                        onChange={this.editEventHandler}
                        type="text"
                        id={item.todoId}    // 각 리스트를 구분하려고 id를 연결
                        name={item.todoId}  // 각 리스트를 구분하려고 id를 연결
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton aria-label="Delete Todo" onClick={this.deleteEventHandler}>
                        <DeleteOutlined />
                    </IconButton>
                </ListItemSecondaryAction>

            </ListItem>
            // <div className="Todo">
            //     <input type="checkbox" id={this.state.item.id} name={this.state.item.id} checked={this.state.item.done} />
            //     <label id={this.state.item.id}>{this.state.item.title}</label>
            // </div>
        )
    }
}

export default Todo;