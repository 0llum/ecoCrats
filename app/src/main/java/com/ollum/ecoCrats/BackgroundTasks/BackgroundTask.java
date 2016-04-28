package com.ollum.ecoCrats.BackgroundTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;

import com.ollum.ecoCrats.Activities.Login;
import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Fragments.ActiveTransportFragment;
import com.ollum.ecoCrats.Fragments.FriendlistFragment;
import com.ollum.ecoCrats.Fragments.SettingsFragment;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.Fragments.StoresFragment;
import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.Utils.Constants;

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
        progressDialog.setCancelable(true);
        progressDialog.setTitle(ctx.getResources().getString(R.string.progressing));
        progressDialog.setMessage(ctx.getResources().getString(R.string.please_wait));
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];

        if (method.equals("signUp")) {

            String username = params[1];
            String password = params[2];
            String email = params[3];

            try {
                URL url = new URL(Constants.reg_url);
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
            String regid = params[3];

            try {
                URL url = new URL(Constants.login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("regid", "UTF-8") + "=" + URLEncoder.encode(regid, "UTF-8");
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
        } else if (method.equals("logout")) {
            String username = params[1];

            try {
                URL url = new URL(Constants.logout_url);
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
        } else if (method.equals("addFriend")) {
            String username_1 = params[1];
            String username_2 = params[2];

            try {
                URL url = new URL(Constants.addFriend_url);
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
                URL url = new URL(Constants.removeFriend_url);
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
                URL url = new URL(Constants.deleteAccount_url);
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
                URL url = new URL(Constants.changeEmail_url);
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
                URL url = new URL(Constants.changePassword_url);
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
                URL url = new URL(Constants.sendMessage_url);
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

                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("buyArea")) {
            String Username = params[1];
            String Region_ID = params[2];
            String Area = params[3];
            String Cost = params[4];

            try {
                URL url = new URL(Constants.buyArea_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&" +
                        URLEncoder.encode("Region_ID", "UTF-8") + "=" + URLEncoder.encode(Region_ID, "UTF-8") + "&" +
                        URLEncoder.encode("Area", "UTF-8") + "=" + URLEncoder.encode(Area, "UTF-8") + "&" +
                        URLEncoder.encode("Cost", "UTF-8") + "=" + URLEncoder.encode(Cost, "UTF-8");
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
        } else if (method.equals("buildStore")) {
            String username = params[1];
            String Region_ID = params[2];

            try {
                URL url = new URL(Constants.buildStore_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("Region_ID", "UTF-8") + "=" + URLEncoder.encode(Region_ID, "UTF-8");
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
        } else if (method.equals("addItem")) {
            String Store_ID = params[1];
            String Item_ID = params[2];
            String Quantity = params[3];

            try {
                URL url = new URL(Constants.addItem_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Store_ID", "UTF-8") + "=" + URLEncoder.encode(Store_ID, "UTF-8") + "&" +
                        URLEncoder.encode("Item_ID", "UTF-8") + "=" + URLEncoder.encode(Item_ID, "UTF-8") + "&" +
                        URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(Quantity, "UTF-8");
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
        } else if (method.equals("transport")) {
            String startStore_ID = params[1];
            String destinationStore_ID = params[2];
            String goodsID = params[3];
            String Quantity = params[4];
            String duration = params[5];

            try {
                URL url = new URL(Constants.transport_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("startStore_ID", "UTF-8") + "=" + URLEncoder.encode(startStore_ID, "UTF-8") + "&" +
                        URLEncoder.encode("destinationStore_ID", "UTF-8") + "=" + URLEncoder.encode(destinationStore_ID, "UTF-8") + "&" +
                        URLEncoder.encode("goodsID", "UTF-8") + "=" + URLEncoder.encode(goodsID, "UTF-8") + "&" +
                        URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(Quantity, "UTF-8") + "&" +
                        URLEncoder.encode("duration", "UTF-8") + "=" + URLEncoder.encode(duration, "UTF-8");
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
        } else if (method.equals("sendECOs")) {
            String sender = params[1];
            String receiver = params[2];
            String ECOs = params[3];

            try {
                URL url = new URL(Constants.sendECOs_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Sender", "UTF-8") + "=" + URLEncoder.encode(sender, "UTF-8") + "&" +
                        URLEncoder.encode("Receiver", "UTF-8") + "=" + URLEncoder.encode(receiver, "UTF-8") + "&" +
                        URLEncoder.encode("ECOs", "UTF-8") + "=" + URLEncoder.encode(ECOs, "UTF-8");
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
        } else if (method.equals("sellItem")) {
            String Goods_ID = params[1];
            String Quantity = params[2];
            String Price = params[3];

            try {
                URL url = new URL(Constants.sellItem_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Goods_ID", "UTF-8") + "=" + URLEncoder.encode(Goods_ID, "UTF-8") + "&" +
                        URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(Quantity, "UTF-8") + "&" +
                        URLEncoder.encode("Price", "UTF-8") + "=" + URLEncoder.encode(Price, "UTF-8");
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
        } else if (method.equals("updateTransport")) {
            try {
                URL url = new URL(Constants.updateTransport_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
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

        if (result != null) {
            switch (result) {
                case ("Signing up successful"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.sign_up_successful, Snackbar.LENGTH_LONG).show();
                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, Login.class));
                    ((Activity) ctx).overridePendingTransition(0, 0);
                    break;
                case ("Login successful"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.login_successful, Snackbar.LENGTH_LONG).show();
                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, MainActivity.class));
                    ((Activity) ctx).overridePendingTransition(0, 0);
                    break;
                case ("Friend added"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_added, Snackbar.LENGTH_LONG).show();
                    FriendlistFragment friendlistFragment = new FriendlistFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, friendlistFragment, "FriendlistFragment")
                            .addToBackStack("FriendlistFragment")
                            .commit();
                    break;
                case ("Friend removed"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_removed, Snackbar.LENGTH_LONG).show();
                    FriendlistFragment friendlistFragment1 = new FriendlistFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, friendlistFragment1, "FriendlistFragment")
                            .addToBackStack("FriendlistFragment")
                            .commit();
                    break;
                case ("Account deleted"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.account_deleted, Snackbar.LENGTH_LONG).show();
                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, Login.class));
                    break;
                case ("Email address successfully changed"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.email_changed, Snackbar.LENGTH_LONG).show();
                    SettingsFragment settingsFragment = new SettingsFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, settingsFragment, "SettingsFragment")
                            .addToBackStack("SettingsFragment")
                            .commit();
                    break;
                case ("Password successfully changed"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.password_changed, Snackbar.LENGTH_LONG).show();
                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, Login.class));
                    break;
                case ("Message sent"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.message_sent, Snackbar.LENGTH_LONG).show();
                    MainActivity.fragmentManager.popBackStack();
                    break;
                case ("Store has been built successfully"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.store_built, Snackbar.LENGTH_LONG).show();
                    StoresFragment storesFragment = new StoresFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, storesFragment, "StoresFragment")
                            .addToBackStack("StoresFragment")
                            .commit();
                    break;
                case ("Transport was successful"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.transport_sent, Snackbar.LENGTH_LONG).show();
                    ActiveTransportFragment activeTransportFragment = new ActiveTransportFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, activeTransportFragment, "ActiveTransportFragment")
                            .addToBackStack("ActiveTransportFragment")
                            .commit();
                    break;
                case ("Item has been sold successfully"):
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.item_sold, Snackbar.LENGTH_LONG).show();
                    StoreDetailsFragment storeDetailsFragment = new StoreDetailsFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, storeDetailsFragment, "StoreDetailsFragment")
                            .addToBackStack("StoreDetailsFragment")
                            .commit();
                    break;
                case "Friend could not be added":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_not_added, Snackbar.LENGTH_LONG).show();
                    break;
                case "You cannot add yourself as a friend":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.add_yourself, Snackbar.LENGTH_LONG).show();
                    break;
                case "User is already your friend":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.user_already_friend, Snackbar.LENGTH_LONG).show();
                    break;
                case "Item could not be added":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.item_not_added, Snackbar.LENGTH_LONG).show();
                    break;
                case "Item has been added successfully":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.item_added, Snackbar.LENGTH_LONG).show();
                    break;
                case "Store could not be built":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.store_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "You already have a Store in this Region":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.store_region, Snackbar.LENGTH_LONG).show();
                    break;
                case "You don't own any Area in this Region":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.no_area, Snackbar.LENGTH_LONG).show();
                    break;
                case "Area could not be bought":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.area_not_bought, Snackbar.LENGTH_LONG).show();
                    break;
                case "Old password is wrong":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.old_password_wrong, Snackbar.LENGTH_LONG).show();
                    break;
                case "DB Connection Error":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.db_error, Snackbar.LENGTH_LONG).show();
                    break;
                case "Login failed, incorrect user data":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.login_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "Login successful, but device could not be registered for GCM":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.login_gcm, Snackbar.LENGTH_LONG).show();
                    break;
                case "Friend could not be removed":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_not_removed, Snackbar.LENGTH_LONG).show();
                    break;
                case "User is not your friend":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.user_not_friend, Snackbar.LENGTH_LONG).show();
                    break;
                case "Item could not be sold":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.item_not_sold, Snackbar.LENGTH_LONG).show();
                    break;
                case "You don't have enough ECOs":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.not_enough_ecos, Snackbar.LENGTH_LONG).show();
                    break;
                case "ECOs could not be sent":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.ecos_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "ECOs successfully sent":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.ecos_sent, Snackbar.LENGTH_LONG).show();
                    break;
                case "Message could not be sent":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.message_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "User could not be found":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.user_not_found, Snackbar.LENGTH_LONG).show();
                    break;
                case "Signing up failed":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.sign_up_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "Email address already exists":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.email_exists, Snackbar.LENGTH_LONG).show();
                    break;
                case "Username already exists":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.username_exists, Snackbar.LENGTH_LONG).show();
                    break;
                case "Loan successfully raised":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.loan_raised, Snackbar.LENGTH_LONG).show();
                    break;
                case "Loan could not be raised":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.loan_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "Transport failed":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.transport_failed, Snackbar.LENGTH_LONG).show();
                    break;
                case "Not enough space in Store":
                    Snackbar.make(MainActivity.coordinatorLayout, R.string.not_enough_space, Snackbar.LENGTH_LONG).show();
                    break;
                case "Transports has been updated":
                    break;
            }
        }
    }
}
