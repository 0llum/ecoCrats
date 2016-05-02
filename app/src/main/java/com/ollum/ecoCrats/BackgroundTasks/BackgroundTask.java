package com.ollum.ecoCrats.BackgroundTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.Login;
import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Fragments.ActiveTransportFragment;
import com.ollum.ecoCrats.Fragments.FriendlistFragment;
import com.ollum.ecoCrats.Fragments.MarketSalesFragment;
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
        } else if (method.equals("upgradeStore")) {
            String storeID = params[1];

            try {
                URL url = new URL(Constants.upgradeStore_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Store_ID", "UTF-8") + "=" + URLEncoder.encode(storeID, "UTF-8");
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
        } else if (method.equals("buyItem")) {
            String salesID = params[1];
            String storeID = params[2];
            String quantity = params[3];
            String cost = params[4];

            try {
                URL url = new URL(Constants.buyItem_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Sales_ID", "UTF-8") + "=" + URLEncoder.encode(salesID, "UTF-8") + "&" +
                        URLEncoder.encode("Store_ID", "UTF-8") + "=" + URLEncoder.encode(storeID, "UTF-8") + "&" +
                        URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(quantity, "UTF-8") + "&" +
                        URLEncoder.encode("Cost", "UTF-8") + "=" + URLEncoder.encode(cost, "UTF-8");
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
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.sign_up_successful, Snackbar.LENGTH_LONG);
                    TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.BLACK);
                    snackbar.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar.show();

                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, Login.class));
                    ((Activity) ctx).overridePendingTransition(0, 0);
                    break;
                case ("Login successful"):
                    Snackbar snackbar1 = Snackbar.make(MainActivity.coordinatorLayout, R.string.login_successful, Snackbar.LENGTH_LONG);
                    TextView textView1 = (TextView) snackbar1.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView1.setTextColor(Color.BLACK);
                    snackbar1.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar1.show();

                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, MainActivity.class));
                    ((Activity) ctx).overridePendingTransition(0, 0);
                    break;
                case ("Friend added"):
                    Snackbar snackbar2 = Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_added, Snackbar.LENGTH_LONG);
                    TextView textView2 = (TextView) snackbar2.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView2.setTextColor(Color.BLACK);
                    snackbar2.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar2.show();

                    FriendlistFragment friendlistFragment = new FriendlistFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, friendlistFragment, "FriendlistFragment")
                            .addToBackStack("FriendlistFragment")
                            .commit();
                    break;
                case ("Friend removed"):
                    Snackbar snackbar3 = Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_removed, Snackbar.LENGTH_LONG);
                    TextView textView3 = (TextView) snackbar3.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView3.setTextColor(Color.BLACK);
                    snackbar3.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar3.show();

                    FriendlistFragment friendlistFragment1 = new FriendlistFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, friendlistFragment1, "FriendlistFragment")
                            .addToBackStack("FriendlistFragment")
                            .commit();
                    break;
                case ("Account deleted"):
                    Snackbar snackbar4 = Snackbar.make(MainActivity.coordinatorLayout, R.string.account_deleted, Snackbar.LENGTH_LONG);
                    TextView textView4 = (TextView) snackbar4.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView4.setTextColor(Color.BLACK);
                    snackbar4.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar4.show();

                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, Login.class));
                    break;
                case ("Email address successfully changed"):
                    Snackbar snackbar5 = Snackbar.make(MainActivity.coordinatorLayout, R.string.email_changed, Snackbar.LENGTH_LONG);
                    TextView textView5 = (TextView) snackbar5.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView5.setTextColor(Color.BLACK);
                    snackbar5.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar5.show();

                    SettingsFragment settingsFragment = new SettingsFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, settingsFragment, "SettingsFragment")
                            .addToBackStack("SettingsFragment")
                            .commit();
                    break;
                case ("Password successfully changed"):
                    Snackbar snackbar6 = Snackbar.make(MainActivity.coordinatorLayout, R.string.password_changed, Snackbar.LENGTH_LONG);
                    TextView textView6 = (TextView) snackbar6.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView6.setTextColor(Color.BLACK);
                    snackbar6.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar6.show();

                    ((Activity) ctx).finish();
                    ctx.startActivity(new Intent(ctx, Login.class));
                    break;
                case ("Message sent"):
                    Snackbar snackbar7 = Snackbar.make(MainActivity.coordinatorLayout, R.string.message_sent, Snackbar.LENGTH_LONG);
                    TextView textView7 = (TextView) snackbar7.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView7.setTextColor(Color.BLACK);
                    snackbar7.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar7.show();

                    MainActivity.fragmentManager.popBackStack();
                    break;
                case ("Store has been built successfully"):
                    Snackbar snackbar8 = Snackbar.make(MainActivity.coordinatorLayout, R.string.store_built, Snackbar.LENGTH_LONG);
                    TextView textView8 = (TextView) snackbar8.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView8.setTextColor(Color.BLACK);
                    snackbar8.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar8.show();

                    StoresFragment storesFragment = new StoresFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, storesFragment, "StoresFragment")
                            .addToBackStack("StoresFragment")
                            .commit();
                    break;
                case ("Transport was successful"):
                    Snackbar snackbar9 = Snackbar.make(MainActivity.coordinatorLayout, R.string.transport_sent, Snackbar.LENGTH_LONG);
                    TextView textView9 = (TextView) snackbar9.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView9.setTextColor(Color.BLACK);
                    snackbar9.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar9.show();

                    ActiveTransportFragment activeTransportFragment = new ActiveTransportFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, activeTransportFragment, "ActiveTransportFragment")
                            .addToBackStack("ActiveTransportFragment")
                            .commit();
                    break;
                case ("Item has been sold successfully"):
                    Snackbar snackbar10 = Snackbar.make(MainActivity.coordinatorLayout, R.string.item_sold, Snackbar.LENGTH_LONG);
                    TextView textView10 = (TextView) snackbar10.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView10.setTextColor(Color.BLACK);
                    snackbar10.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar10.show();

                    StoreDetailsFragment storeDetailsFragment = new StoreDetailsFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, storeDetailsFragment, "StoreDetailsFragment")
                            .addToBackStack("StoreDetailsFragment")
                            .commit();
                    break;
                case "Friend could not be added":
                    Snackbar snackbar11 = Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_not_added, Snackbar.LENGTH_LONG);
                    TextView textView11 = (TextView) snackbar11.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView11.setTextColor(Color.BLACK);
                    snackbar11.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar11.show();

                    break;
                case "You cannot add yourself as a friend":
                    Snackbar snackbar12 = Snackbar.make(MainActivity.coordinatorLayout, R.string.add_yourself, Snackbar.LENGTH_LONG);
                    TextView textView12 = (TextView) snackbar12.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView12.setTextColor(Color.BLACK);
                    snackbar12.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar12.show();

                    break;
                case "User is already your friend":
                    Snackbar snackbar13 = Snackbar.make(MainActivity.coordinatorLayout, R.string.user_already_friend, Snackbar.LENGTH_LONG);
                    TextView textView13 = (TextView) snackbar13.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView13.setTextColor(Color.BLACK);
                    snackbar13.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar13.show();

                    break;
                case "Item could not be added":
                    Snackbar snackbar14 = Snackbar.make(MainActivity.coordinatorLayout, R.string.item_not_added, Snackbar.LENGTH_LONG);
                    TextView textView14 = (TextView) snackbar14.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView14.setTextColor(Color.BLACK);
                    snackbar14.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar14.show();

                    break;
                case "Item has been added successfully":
                    Snackbar snackbar15 = Snackbar.make(MainActivity.coordinatorLayout, R.string.item_added, Snackbar.LENGTH_LONG);
                    TextView textView15 = (TextView) snackbar15.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView15.setTextColor(Color.BLACK);
                    snackbar15.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar15.show();

                    break;
                case "Store could not be built":
                    Snackbar snackbar16 = Snackbar.make(MainActivity.coordinatorLayout, R.string.store_failed, Snackbar.LENGTH_LONG);
                    TextView textView16 = (TextView) snackbar16.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView16.setTextColor(Color.BLACK);
                    snackbar16.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar16.show();

                    break;
                case "You already have a Store in this Region":
                    Snackbar snackbar17 = Snackbar.make(MainActivity.coordinatorLayout, R.string.store_region, Snackbar.LENGTH_LONG);
                    TextView textView17 = (TextView) snackbar17.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView17.setTextColor(Color.BLACK);
                    snackbar17.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar17.show();

                    break;
                case "You don't own any Area in this Region":
                    Snackbar snackbar18 = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_area, Snackbar.LENGTH_LONG);
                    TextView textView18 = (TextView) snackbar18.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView18.setTextColor(Color.BLACK);
                    snackbar18.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar18.show();

                    break;
                case "Area could not be bought":
                    Snackbar snackbar19 = Snackbar.make(MainActivity.coordinatorLayout, R.string.area_not_bought, Snackbar.LENGTH_LONG);
                    TextView textView19 = (TextView) snackbar19.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView19.setTextColor(Color.BLACK);
                    snackbar19.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar19.show();

                    break;
                case "Old password is wrong":
                    Snackbar snackbar20 = Snackbar.make(MainActivity.coordinatorLayout, R.string.old_password_wrong, Snackbar.LENGTH_LONG);
                    TextView textView20 = (TextView) snackbar20.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView20.setTextColor(Color.BLACK);
                    snackbar20.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar20.show();

                    break;
                case "DB Connection Error":
                    Snackbar snackbar21 = Snackbar.make(MainActivity.coordinatorLayout, R.string.db_error, Snackbar.LENGTH_LONG);
                    TextView textView21 = (TextView) snackbar21.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView21.setTextColor(Color.BLACK);
                    snackbar21.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar21.show();

                    break;
                case "Login failed, incorrect user data":
                    Snackbar snackbar22 = Snackbar.make(MainActivity.coordinatorLayout, R.string.login_failed, Snackbar.LENGTH_LONG);
                    TextView textView22 = (TextView) snackbar22.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView22.setTextColor(Color.BLACK);
                    snackbar22.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar22.show();

                    break;
                case "Login successful, but device could not be registered for GCM":
                    Snackbar snackbar23 = Snackbar.make(MainActivity.coordinatorLayout, R.string.login_gcm, Snackbar.LENGTH_LONG);
                    TextView textView23 = (TextView) snackbar23.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView23.setTextColor(Color.BLACK);
                    snackbar23.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar23.show();

                    break;
                case "Friend could not be removed":
                    Snackbar snackbar24 = Snackbar.make(MainActivity.coordinatorLayout, R.string.friend_not_removed, Snackbar.LENGTH_LONG);
                    TextView textView24 = (TextView) snackbar24.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView24.setTextColor(Color.BLACK);
                    snackbar24.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar24.show();

                    break;
                case "User is not your friend":
                    Snackbar snackbar25 = Snackbar.make(MainActivity.coordinatorLayout, R.string.user_not_friend, Snackbar.LENGTH_LONG);
                    TextView textView25 = (TextView) snackbar25.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView25.setTextColor(Color.BLACK);
                    snackbar25.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar25.show();

                    break;
                case "Item could not be sold":
                    Snackbar snackbar26 = Snackbar.make(MainActivity.coordinatorLayout, R.string.item_not_sold, Snackbar.LENGTH_LONG);
                    TextView textView26 = (TextView) snackbar26.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView26.setTextColor(Color.BLACK);
                    snackbar26.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar26.show();

                    break;
                case "You don't have enough ECOs":
                    Snackbar snackbar27 = Snackbar.make(MainActivity.coordinatorLayout, R.string.not_enough_ecos, Snackbar.LENGTH_LONG);
                    TextView textView27 = (TextView) snackbar27.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView27.setTextColor(Color.BLACK);
                    snackbar27.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar27.show();

                    break;
                case "ECOs could not be sent":
                    Snackbar snackbar28 = Snackbar.make(MainActivity.coordinatorLayout, R.string.ecos_failed, Snackbar.LENGTH_LONG);
                    TextView textView28 = (TextView) snackbar28.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView28.setTextColor(Color.BLACK);
                    snackbar28.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar28.show();
                    break;
                case "ECOs successfully sent":
                    Snackbar snackbar29 = Snackbar.make(MainActivity.coordinatorLayout, R.string.ecos_sent, Snackbar.LENGTH_LONG);
                    TextView textView29 = (TextView) snackbar29.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView29.setTextColor(Color.BLACK);
                    snackbar29.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar29.show();
                    break;
                case "Message could not be sent":
                    Snackbar snackbar30 = Snackbar.make(MainActivity.coordinatorLayout, R.string.message_failed, Snackbar.LENGTH_LONG);
                    TextView textView30 = (TextView) snackbar30.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView30.setTextColor(Color.BLACK);
                    snackbar30.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar30.show();

                    break;
                case "User could not be found":
                    Snackbar snackbar31 = Snackbar.make(MainActivity.coordinatorLayout, R.string.user_not_found, Snackbar.LENGTH_LONG);
                    TextView textView31 = (TextView) snackbar31.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView31.setTextColor(Color.BLACK);
                    snackbar31.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar31.show();

                    break;
                case "Signing up failed":
                    Snackbar snackbar32 = Snackbar.make(MainActivity.coordinatorLayout, R.string.sign_up_failed, Snackbar.LENGTH_LONG);
                    TextView textView32 = (TextView) snackbar32.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView32.setTextColor(Color.BLACK);
                    snackbar32.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar32.show();

                    break;
                case "Email address already exists":
                    Snackbar snackbar33 = Snackbar.make(MainActivity.coordinatorLayout, R.string.email_exists, Snackbar.LENGTH_LONG);
                    TextView textView33 = (TextView) snackbar33.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView33.setTextColor(Color.BLACK);
                    snackbar33.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar33.show();

                    break;
                case "Username already exists":
                    Snackbar snackbar34 = Snackbar.make(MainActivity.coordinatorLayout, R.string.username_exists, Snackbar.LENGTH_LONG);
                    TextView textView34 = (TextView) snackbar34.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView34.setTextColor(Color.BLACK);
                    snackbar34.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar34.show();

                    break;
                case "Loan successfully raised":
                    Snackbar snackbar35 = Snackbar.make(MainActivity.coordinatorLayout, R.string.loan_raised, Snackbar.LENGTH_LONG);
                    TextView textView35 = (TextView) snackbar35.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView35.setTextColor(Color.BLACK);
                    snackbar35.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar35.show();

                    break;
                case "Loan could not be raised":
                    Snackbar snackbar36 = Snackbar.make(MainActivity.coordinatorLayout, R.string.loan_failed, Snackbar.LENGTH_LONG);
                    TextView textView36 = (TextView) snackbar36.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView36.setTextColor(Color.BLACK);
                    snackbar36.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar36.show();

                    break;
                case "Transport failed":
                    Snackbar snackbar37 = Snackbar.make(MainActivity.coordinatorLayout, R.string.transport_failed, Snackbar.LENGTH_LONG);
                    TextView textView37 = (TextView) snackbar37.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView37.setTextColor(Color.BLACK);
                    snackbar37.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar37.show();

                    break;
                case "Not enough space in Store":
                    Snackbar snackbar38 = Snackbar.make(MainActivity.coordinatorLayout, R.string.not_enough_space, Snackbar.LENGTH_LONG);
                    TextView textView38 = (TextView) snackbar38.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView38.setTextColor(Color.BLACK);
                    snackbar38.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar38.show();
                    break;
                case "Transports has been updated":
                    break;
                case "Store has been upgraded successfully":
                    Snackbar snackbar39 = Snackbar.make(MainActivity.coordinatorLayout, R.string.store_upgraded, Snackbar.LENGTH_LONG);
                    TextView textView39 = (TextView) snackbar39.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView39.setTextColor(Color.BLACK);
                    snackbar39.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar39.show();
                    break;
                case "Store could not be upgraded":
                    Snackbar snackbar40 = Snackbar.make(MainActivity.coordinatorLayout, R.string.store_upgrade_failed, Snackbar.LENGTH_LONG);
                    TextView textView40 = (TextView) snackbar40.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView40.setTextColor(Color.BLACK);
                    snackbar40.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar40.show();
                    break;
                case "Area has been bought successfully":
                    Snackbar snackbar41 = Snackbar.make(MainActivity.coordinatorLayout, R.string.area_bought, Snackbar.LENGTH_LONG);
                    TextView textView41 = (TextView) snackbar41.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView41.setTextColor(Color.BLACK);
                    snackbar41.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar41.show();
                    break;
                case "Item has been bought successfully":
                    Snackbar snackbar42 = Snackbar.make(MainActivity.coordinatorLayout, R.string.item_bought, Snackbar.LENGTH_LONG);
                    TextView textView42 = (TextView) snackbar42.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView42.setTextColor(Color.BLACK);
                    snackbar42.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar42.show();

                    MarketSalesFragment marketSalesFragment = new MarketSalesFragment();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.mainContent, marketSalesFragment, "MarketSalesFragment")
                            .addToBackStack("MarketSalesFragment")
                            .commit();
                    break;
                case "Item could not be bought":
                    Snackbar snackbar43 = Snackbar.make(MainActivity.coordinatorLayout, R.string.item_bought_failed, Snackbar.LENGTH_LONG);
                    TextView textView43 = (TextView) snackbar43.getView().findViewById(android.support.design.R.id.snackbar_text);
                    textView43.setTextColor(Color.BLACK);
                    snackbar43.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                    snackbar43.show();
                    break;
            }
        }
    }
}
