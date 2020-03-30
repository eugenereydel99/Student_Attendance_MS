package com.example.student_attendance_ms.main.ui.attendance.methods

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.example.student_attendance_ms.R

private const val CAMERA_REQUEST_CODE = 10
private val CAMERA_PERMISSION  = arrayOf(Manifest.permission.CAMERA)

class ScannerFragment : Fragment() {

    private lateinit var barcodeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val barcodeView = inflater.inflate(R.layout.scanner_layout, container,false)
        scannerView = barcodeView.findViewById(R.id.scanner_view)

        return barcodeView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (context?.let { ContextCompat.checkSelfPermission(it, CAMERA_PERMISSION.toString()) } == PackageManager.PERMISSION_DENIED){
            requestPermissions(CAMERA_PERMISSION, CAMERA_REQUEST_CODE)
        } else {
            startScanning()
        }
    }

    private fun startScanning(){
        val activity = requireActivity()
        barcodeScanner = CodeScanner(activity, scannerView)

        barcodeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
            }
        }

        barcodeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, "Camera initialization error: ${it.message}",
                        Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            barcodeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startScanning()
            } else {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::barcodeScanner.isInitialized){
            barcodeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::barcodeScanner.isInitialized){
            barcodeScanner.releaseResources()
        }
        super.onPause()
    }
}
