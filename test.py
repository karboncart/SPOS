import tkinter as tk
from tkinter import ttk,messagebox

def on_login():
	email=email_entry.get()
	password=password_entry.get()
	
	if(email=="admin" and password=="1234" ):
		messagebox.showinfo("Login Succesfull","Welcome Back !")
	else:
		messagebox.showerror("Login Failed","Invalid User Name or Password")

app=tk.Tk()
app.title("Login Interface")


left_frame=tk.Frame(app,width=300,height=480,bg="light gray")
left_frame.pack(expand=True,side="left",fill="y")

frame=tk.Frame(app,width=300,height=480,bg="white")
frame.pack(expand=True,side="right",fill="both")

title_Label=tk.Label(frame,text="Welcome Back !",fg="purple",bg="white",font=("Arial Bold",24))
title_Label.pack(anchor="w",pady=(50,5),padx=(25,0))

sub_title=tk.Label(frame,text="Sign in to your account",fg="gray",bg="white",font=("Arial",12))
sub_title.pack(anchor="w",padx=(25,0))

email_Label=tk.Label(frame,text="User Name",fg="purple",bg="white",font=("Arial",14))
email_Label.pack(anchor="w",pady=(38,0),padx=(25,0))

email_entry=ttk.Entry(frame,width=28)
email_entry.pack(anchor="w",padx=(25,0))

password_Label=tk.Label(frame,text="Password",fg="purple",bg="white",font=("Arial",14))
password_Label.pack(anchor="w",pady=(21,0),padx=(25,0))

password_entry=ttk.Entry(frame,width=28,show="*")
password_entry.pack(anchor="w",padx=(25,0))

login_Label=tk.Button(frame,text="Login",fg="white",bg="purple",font=("Arial",12),width=22,height=1,command=on_login)
login_Label.pack(anchor="w",pady=(40,0),padx=(25,0))

google_Label=tk.Button(frame,text="Continue with google",fg="light gray",bg="purple",width=22,height=1)
google_Label.pack(anchor="w",pady=(21,0),padx=(25,0))
				
app.mainloop()				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
