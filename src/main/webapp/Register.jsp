<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS for background */
        .custom-bg {
            background-color: gray; /* Replace with your desired gradient */
            color: white; /* Text color */
        }
        /* Animation for form */
        @keyframes moveForm {
            0% {
                transform: translateY(-50px); /* Move form up by 50px */
                opacity: 0;
            }
            100% {
                transform: translateY(0); /* Move form back to original position */
                opacity: 1;
            }
        }
        /* Apply animation to form */
        .animate-form {
            animation: moveForm 0.8s ease-in-out;
        }
    </style>
</head>
<body class="custom-bg">
<section class="vh-80">
    <div class="container py-5 h-80">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center animate-form"> <!-- Add animate-form class here -->

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="mb-2" style="text-transform: capitalize">Register</h2>

                            <form action="NewServlet">
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="fname" style="margin-right: 350px">Firstname</label>
                                    <input type="text" id="fname" name="fname" class="form-control form-control-lg" />
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="lname" style="margin-right: 350px">Lastname</label>
                                    <input type="text" id="lname" name="lname" class="form-control form-control-lg" />
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="phone" style="margin-right: 350px">Phone</label>
                                    <input type="text" id="phone" name="phone" class="form-control form-control-lg" />
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="uname" style="margin-right: 350px">Username</label>
                                    <input type="text" id="uname" name="uname" class="form-control form-control-lg" />
                                </div>
                                <label class="form-label" for="pass" style="margin-right: 350px">Password</label>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="pass" name="pass" class="form-control form-control-lg" />
                                </div>
                                <input name="register" type="hidden">
                                <input class="btn btn-outline-light btn-lg px-5" type="submit" name="log" value="Register"/>
                            </form>

                        </div>

                        <div>
                            <p class="mb-0">Already have login <a href="Login.jsp" class="text-white-50 fw-bold">Login</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
