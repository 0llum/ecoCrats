package com.ollum.ecoCrats.BackgroundTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;

import com.ollum.ecoCrats.Activities.Login;
import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Fragments.FriendlistFragment;
import com.ollum.ecoCrats.Fragments.SettingsFragment;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.Fragments.StoresFragment;
import com.ollum.ecoCrats.R;

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
        String reg_url = "http://0llum.bplaced.net/ecoCrats/SignUp.php";
        String login_url = "http://0llum.bplaced.net/ecoCrats/Login.php";
        String logout_url = "http://0llum.bplaced.net/ecoCrats/Logout.php";
        String addFriend_url = "http://0llum.bplaced.net/ecoCrats/AddFriend.php";
        String removeFriend_url = "http://0llum.bplaced.net/ecoCrats/RemoveFriend.php";
        String deleteAccount_url = "http://0llum.bplaced.net/ecoCrats/DeleteAccount.php";
        String changeEmail_url = "http://0llum.bplaced.net/ecoCrats/ChangeEmail.php";
        String changePassword_url = "http://0llum.bplaced.net/ecoCrats/ChangePassword.php";
        String sendMessage_url = "http://0llum.bplaced.net/ecoCrats/SendMessage.php";
        String buyArea_url = "http://0llum.bplaced.net/ecoCrats/BuyArea.php";
        String buildStore_url = "http://0llum.bplaced.net/ecoCrats/BuildStore.php";
        String addItem_url = "http://0llum.bplaced.net/ecoCrats/AddItem.php";
        String transport_url = "http://0llum.bplaced.net/ecoCrats/Transport.php";
        String sendECOs_url = "http://0llum.bplaced.net/ecoCrats/SendECOs.php";
        String sellItem_url = "http://0llum.bplaced.net/ecoCrats/SellItem.php";
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
            String regid = params[3];

            try {
                URL url = new URL(login_url);
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
                URL url = new URL(logout_url);
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
                URL url = new URL(buyArea_url);
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
                URL url = new URL(buildStore_url);
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
                URL url = new URL(addItem_url);
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
            String itemName = params[2];
            String Quantity = params[3];
            String username = params[4];
            String destinationStore_ID = params[5];

            try {
                URL url = new URL(transport_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("startStore_ID", "UTF-8") + "=" + URLEncoder.encode(startStore_ID, "UTF-8") + "&" +
                        URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8") + "&" +
                        URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(Quantity, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("destinationStore_ID", "UTF-8") + "=" + URLEncoder.encode(destinationStore_ID, "UTF-8");
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
                URL url = new URL(sendECOs_url);
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
                URL url = new URL(sellItem_url);
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

        Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, result, Snackbar.LENGTH_LONG);
        snackbar.show();

        switch (result) {
            case ("Signing up successful"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Login.class));
                ((Activity) ctx).overridePendingTransition(0, 0);
                break;
            case ("Login successful"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, MainActivity.class));
                ((Activity) ctx).overridePendingTransition(0, 0);
                break;
            case ("Friend added"):
                FriendlistFragment friendlistFragment = new FriendlistFragment();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, friendlistFragment, "FriendlistFragment")
                        .addToBackStack("FriendlistFragment")
                        .commit();
                break;
            case ("Friend removed"):
                FriendlistFragment friendlistFragment1 = new FriendlistFragment();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, friendlistFragment1, "FriendlistFragment")
                        .addToBackStack("FriendlistFragment")
                        .commit();
                break;
            case ("Account deleted"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Login.class));
                break;
            case ("Email address successfully changed"):
                SettingsFragment settingsFragment = new SettingsFragment();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, settingsFragment, "SettingsFragment")
                        .addToBackStack("SettingsFragment")
                        .commit();
                break;
            case ("Password successfully changed"):
                ((Activity) ctx).finish();
                ctx.startActivity(new Intent(ctx, Login.class));
                break;
            case ("Message sent"):
                MainActivity.fragmentManager.popBackStack();
                break;
            case ("Store has been built successfully"):
                StoresFragment storesFragment = new StoresFragment();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, storesFragment, "StoresFragment")
                        .addToBackStack("StoresFragment")
                        .commit();
            case ("Transport was successful"):
                StoresFragment storesFragment1 = new StoresFragment();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, storesFragment1, "StoresFragment")
                        .addToBackStack("StoresFragment")
                        .commit();
            case ("Item has been sold successfully"):
                StoreDetailsFragment storeDetailsFragment = new StoreDetailsFragment();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, storeDetailsFragment, "StoreDetailsFragment")
                        .addToBackStack("StoreDetailsFragment")
                        .commit();
        }
    }
}
