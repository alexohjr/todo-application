import React from "react";
import { signin } from "./service/ApiService";
import { Button, TextField, Grid, Typography, Container, Link } from "@material-ui/core";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        // this.reactAlert = this.useAlert;
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const email = data.get("email");
        const password = data.get("password");

        console.log("email : " + email);
        console.log("pw : " + password);

        signin({email: email, password: password});

        // if(null == email || null == password) {
        //     // ApiService의 signin 메서드를 사용해 로그인
        //     signin({email: email, password: password});
        // } else {
        //     // useAlert().show('fdfd');
        //     alert("올바르게 입력하세요.");
        // }

    }

    render() {
        return (
            <Container component="main" maxWidth="xs" style={{marginTop: "8%"}}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography component="h1" variant="h5">
                            Login
                        </Typography>
                    </Grid>
                </Grid>

                <form noValidate onSubmit={this.handleSubmit}>
                    {" "}
                    {/* submit 버튼을 클릭하면 handleSubmit이 실행됨 */}
                    <Grid container spacing={2}>

                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="email"
                                label="E-mail"
                                name="email"
                                autoComplete="email"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="password"
                                label="Password"
                                name="password"
                                type="password"
                                autoComplete="current-password"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >
                                Login
                            </Button>
                        </Grid>

                        <Grid item xs={12}>
                            <Grid container justifyContent="flex-end">
                                <Link href="/signup" variant="body2">
                                    <Grid item>계정이 없습니까? 여기서 가입 하세요.</Grid>
                                </Link>
                            </Grid>
                        </Grid>

                    </Grid>

                </form>
            </Container>
        );
    }
}

export default Login;