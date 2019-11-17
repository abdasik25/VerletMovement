package by.bsuir.verlet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import by.bsuir.verlet.helper.ViewAnimationHelper;
import by.bsuir.verlet.model.Triangle;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {


    private EditText forceValue;
    private EditText massValue;
    private EditText angleValue;
    private EditText xCoordinateValue;
    private EditText yCoordinateValue;
    private Button startButton;
    private ViewAnimationHelper customAreaView;
    private Triangle triangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customAreaView = findViewById(R.id.customView);
        xCoordinateValue = findViewById(R.id.xCoordinate);
        yCoordinateValue = findViewById(R.id.yCoordinate);
        forceValue = findViewById(R.id.forceValue);
        massValue = findViewById(R.id.massValue);
        angleValue = findViewById(R.id.angleValue);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener((v) -> startButtonClicked());
    }

    private void startButtonClicked() {
        triangle = new Triangle();

        int x = Integer.parseInt(xCoordinateValue.getText().toString());
        triangle.setX(x);

        int y = Integer.parseInt(yCoordinateValue.getText().toString());
        triangle.setY(y);

        double force = Double.parseDouble(forceValue.getText().toString());
        triangle.setForce(force);

        double mass = Double.parseDouble(massValue.getText().toString());
        triangle.setMass(mass);

        double angle = Double.parseDouble(angleValue.getText().toString());
        triangle.setAngle(angle);

        triangle.calculateA();

        customAreaView.start(triangle);
    }

}
