# Introduction

The messages that vmdv and proof systems send to each other consists of two parts: 

​	**commands** and **feedbacks**.

Both proof systems or vmdv can send **commands** to each other and, for each **command** sent by one side, there is a **feedback** message sent by the other side. 


## 1. Commands

* Create Session (proof systems —> vmdv):

  ```json
  {
    "type": "create_session",
    "session_id": string,
    "session_descr": string,
    "graph_type": string
  }
  ```

  Remark: `graph_type` can be either `"Tree"` or `"DiGraph"`.

* Remove Session (proof systems —> vmdv):

  ```json
  {
    "type": "remove_session",
    "session_id": string
  }
  ```


* Add node (proof systems —> vmdv):

  ```json
  {
    "type": "add_node",
    "session_id": string,
    "node":
      {
  		"id": string,
        	"label": string,
        	"state": string
      }
  }
  ```


- Add edge (proof systems —> vmdv):

  ```json
  {
    "type": "add_edge",
    "session_id": string,
    "from_id": string,
    "to_id": string,
    "label": string
  }
  ```


- Change node state (proof systems —> vmdv):

  ```json
  {
    "type": "change_node_state",
    "session_id": string,
    "node_id": string
  }
  ```

- Highlight node (vmdv <—> proof systems):

  ```json
  {
    "type": "highlight_node",
    "session_id": string,
    "node_id": string
  }
  ```

## 2. Feedback for commands

Feedback (vmdv —> proof systems):

```json
{
  "type": "feedback",
  "session_id": string,
  "status": "OK"
}
```

or 

```json
{
  "type": "feedback",
  "session_id": string,
  "status": "Fail",
  "error_msg": string
}
```

