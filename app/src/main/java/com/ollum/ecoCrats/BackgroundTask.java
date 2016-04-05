package com.ollum.ecoCrats;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;
    ProgressDialog progressDialog;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
        progressDialog = new ProgressDialog(ctx);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Progressing");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://0llum.bplaced.net/ecoCrats/SignUp.php";
        String login_url = "http://0llum.bplaced.net/ecoCrats/Login.php";
        String addFriend_url = "http://0llum.bplaced.net/ecoCrats/AddFriend.php";
        String removeFriend_url = "http://0llum.bplaced.net/ecoCrats/RemoveFriend.php";
        String deleteAccount_url = "http://0llum.bplaced.net/ecoCrats/DeleteAccount.php";
        String changeEmail_url = "http://0llum.bplaced.net/ecoCrats/ChangeEmail.php";
        String changePassword_url = "http://0llum.bplaced.net/ecoCrats/ChangePassword.php";
        String sendMessage_url = "http://0llum.bplaced.net/ecoCrats/SendMessage.php";
        String method = params[0];

        if (method.equals("signUp")) {

            String username = params[1];
            String password = params[2];
            String email = params[3];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            String username = params[1];
            String password = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("addFriend")) {
            String username_1 = params[1];
            String username_2 = params[2];

            try {
                URL url = new URL(addFriend_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username_1", "UTF-8") + "=" + URLEncoder.encode(username_1, "UTF-8") + "&" +
                        URLEncoder.encode("username_2", "UTF-8") + "=" + URLEncoder.encode(username_2, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("removeFriend")) {
            String username_1 = params[1];
            String username_2 = params[2];

            try {
                URL url = new URL(removeFriend_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username_1", "UTF-8") + "=" + URLEncoder.encode(username_1, "UTF-8") + "&" +
                        URLEncoder.encode("username_2", "UTF-8") + "=" + URLEncoder.encode(username_2, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("deleteAccount")) {
            String username = params[1];

            try {
                URL url = new URL(deleteAccount_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("changeEmail")) {
            String username = params[1];
            String email = params[2];

            try {
                URL url = new URL(changeEmail_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("changePassword")) {
            String username = params[1];
            String oldPassword = params[2];
            String newPassword = params[3];

            try {
                URL url = new URL(changePassword_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("oldPassword", "UTF-8") + "=" + URLEncoder.encode(oldPassword, "UTF-8") + "&" +
                        URLEncoder.encode("newPassword", "UTF-8") + "=" + URLEncoder.encode(newPassword, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("sendMessage")) {
            String sender = params[1];
            String receiver = params[2];
            String subject = params[3];
            String message = params[4];

            try {
                URL url = new URL(sendMessage_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("sender", "UTF-8") + "=" + URLEncoder.encode(sender, "UTF-8") + "&" +
                        URLEncoder.encode("receiver", "UTF-8") + "=" + URLEncoder.encode(receiver, "UTF-8") + "&" +
                        URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject, "UTF-8") + "&" +
                        URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.d("debug", response.trim());
                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

    @Override
    protected void onPostExecute(String result) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        switch (result) {
            case ("Signing up successful"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                ctx.startActivity(new Intent(ctx, Login.class));
                ((Activity) ctx).overridePendingTransition(0, 0);
                break;
            case ("Signing up failed"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Username already exists"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Email address already exists"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Login successful"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                ctx.startActivity(new Intent(ctx, Menu.class));
                ((Activity) ctx).overridePendingTransition(0, 0);
                break;
            case ("Login failed, incorrect user data"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Friend added"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Friendlist.class));
                ((Activity) ctx).overridePendingTransition(0, 0);
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("User could not be found"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("User is already your friend"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("You cannot add yourself as a friend"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Friend could not be added"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Friend removed"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Friendlist.class));
                ((Activity) ctx).overridePendingTransition(0, 0);
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("User is not your friend"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Friend could not be removed"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Account deleted"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Login.class));
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Email address successfully changed"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Settings.class));
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Password successfully changed"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Login.class));
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Old password is wrong"):
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Message sent"):
                ((Activity) ctx).finish();
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
            case ("Message could not be sent"):
                ((Activity) ctx).finish();
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
