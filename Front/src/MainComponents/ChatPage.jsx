import "../Styles/ChatStyles.css"
import {useState} from "react";

function Chat() {
    let [count, setCount] = useState(0);
    let TextHeight = 7
    let ClearPos = 9

    const que = document.getElementById("quest")
    const cl = document.getElementById("clear")
    const len = document.getElementById("len")
    const checkLength = (event) => {
        setCount(count = event.target.value.length)
        if (event.target.value.length > 95 && event.target.value.length !== 0 && TextHeight !== 10) {
            TextHeight += 3
            ClearPos += 3
            que.style.minHeight = `${TextHeight}vh`
            cl.style.bottom = `${ClearPos}vh`
            len.style.bottom = `${ClearPos}vh`
        }
        if (event.target.value.length < 95) {
            que.style.minHeight = `7vh`
            cl.style.bottom = '9vh'
            len.style.bottom = `7.5vh`
        }
    }

    const clearChat = () => {
        let chat = document.getElementById("chat")
        let children = chat.children
        for (let i = 0; i < children.length; i++) {
            if (children[i].className === "question" || children[i].className === "answer") {
                children[i].remove()
                i--
            }
        }
    }

    return (
        <>
            <div id="chat" className="chatContainer">
                <p id="len" className="length">{count}/2500</p>
                <p className="question">TestQ</p>
                <p className="answer">TestA</p>
                <div className="textArea">
                    <textarea id="quest" name="question" className="inputArea" maxLength="2500"
                              onChange={() => checkLength(event)}></textarea>
                    <button id="clear" className="ClearBtn" onClick={clearChat}>Clear</button>
                    <button id="enter" className="EnterBtn">Enter</button>
                </div>
            </div>
        </>
    )
}

export default Chat