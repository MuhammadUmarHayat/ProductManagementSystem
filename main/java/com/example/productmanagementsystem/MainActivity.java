package com.example.productmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //etName,etPrice,btnSave,btnViewAll,btnSearch,btnEdit,btnDelete
  EditText etID1,etName1,etPrice1;
Button btnSave1,btnViewAll1,btnSearch1,btnEdit1,btnDelete1;
Spinner spQty1;
    ProductTable table;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName1=findViewById(R.id.etName);
        etID1=findViewById(R.id.etID);
        etPrice1=findViewById(R.id.etPrice);
        spQty1 =findViewById(R.id.spQty);
        btnSave1=findViewById(R.id.btnSave);
        btnViewAll1 =findViewById(R.id.btnViewAll);
        btnSearch1=findViewById(R.id.btnSearch);
        btnEdit1=findViewById(R.id.btnEdit);
        btnDelete1 =findViewById(R.id.btnDelete);

        table=new ProductTable(this);
        Product product=new Product();
        btnSave1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                String name = etName1.getText().toString();
                String q = spQty1.getSelectedItem().toString();

                int qty = Integer.getInteger(q);
                String p = etPrice1.getText().toString();
                int price = Integer.getInteger(p);
                product.setName(name);
                product.setPrice(price);
                product.setQty(qty);

                table.addProduct(product);
                    Toast.makeText(MainActivity.this, "Product is saved ", Toast.LENGTH_SHORT).show();

                }
                catch (Exception exp)
                {
                    Toast.makeText(MainActivity.this, "Error: Please enter correct data", Toast.LENGTH_SHORT).show();


                }

            }
        });

        btnViewAll1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ViewAllProducts();
            }
        });
        btnSearch1.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            SearchProducts();
        }
    });
        btnEdit1.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            try {
                String i = etID1.getText().toString();
                int id = Integer.getInteger(i);

                String name = etName1.getText().toString();
                String q = spQty1.getSelectedItem().toString();

                int qty = Integer.getInteger(q);
                String p = etPrice1.getText().toString();
                int price = Integer.getInteger(p);

                boolean result = table.updateProduct(id, name, qty, price);
                if (result) {
                    Toast.makeText(MainActivity.this, "Record is updated ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Error: Please enter correct data", Toast.LENGTH_SHORT).show();

                }
            }
            catch (Exception exp)
            {
                Toast.makeText(MainActivity.this, "Error: Please enter correct data", Toast.LENGTH_SHORT).show();

            }
        }
    });
        btnDelete1.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            try
            {
                String i = etID1.getText().toString();
                int id = Integer.getInteger(i);
Boolean result= table.deleteProduct(id);

                if (result) {
                    Toast.makeText(MainActivity.this, "Record is Deleted ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Error: Please enter correct id", Toast.LENGTH_SHORT).show();

                }

            }
            catch (Exception exp)
        {
            Toast.makeText(MainActivity.this, "Error: Please enter correct id", Toast.LENGTH_SHORT).show();

        }
        }
    });





    }//end on create
//function defination

    private void ViewAllProducts()
    {
        Cursor res = table.getAllProducts();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }


        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext())
        {

            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Product Name :" + res.getString(1) + "\n");
            buffer.append("Price  :" + res.getString(2) + "\n");
            buffer.append("Quantity :" + res.getString(3) + "\n");


        }//while
        showMessage("Our Products ",buffer.toString());

    }



    private void SearchProducts()
    {
        String name=etName1.getText().toString();
        Cursor res = table.getProduct(name);// productTable.searchData(id);
        if(res.getCount() == 0)
        {
            // show message
            showMessage("Error","Nothing found");
            return;
        }


        StringBuffer buffer = new StringBuffer();
//insertData(String productName,String Packing,int purchasePrice,int SalePrice,String Manufactures,int qty)
        while (res.moveToNext()) {

            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Product Name :" + res.getString(1) + "\n");
            buffer.append("Price  :" + res.getString(2) + "\n");
            buffer.append("Quantity :" + res.getString(3) + "\n");


        }//while
        showMessage("Product Information  ",buffer.toString());
//////////////
    }//end searchProduct
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






}