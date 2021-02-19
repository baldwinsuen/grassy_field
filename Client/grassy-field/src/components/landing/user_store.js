import { store as createStore } from "react-easy-state";

export const user_store = createStore({
    username: "",
    user_id: -1,
    logged_in: false
});

export const createUser = async (username) => {
    fetch('http://localhost:8080/grassyfield/create_user/' + username , {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            
        }
    })
    .then(response => {
        if(response.status === 200) {
            console.log("User was created successfully")
            return response.json()
        }
        else {
            return response
        }
    }).then(resp => { 
        console.log(resp )
        return resp })
    .catch(err => console.log(err))
}

export const findUser = async (username) => {
    fetch('http://localhost:8080/grassyfield/user/' + username , {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            
        }
    })
    .then(response => {
        if(response.status === 200) {
            console.log("User logged in successfully")
            return response.json()
        }
        else {
            return response
        }
    })
    .then(response => {
        
        user_store.logged_in = true
        user_store.username = response.name
        user_store.user_id = response.id

        console.log("Logged In " + user_store.username)
    })
    .catch(err => console.log(err))

    

}


