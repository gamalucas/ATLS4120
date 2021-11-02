//
//  ViewController.swift
//  powerApp
//
//  Created by LUCAS GAMA on 9/28/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var powerLabel: UILabel!
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var powerStepper: UIStepper!
    @IBOutlet weak var baseNumber: UITextField!
    
    @IBAction func updateStepper(_ sender: UIStepper) {
        if powerStepper.value > 0 {
            powerLabel.text = String(format: "%.0f", powerStepper.value) + " - Power"
        }
        else{
            powerLabel.text = "0 - Power"
        }
        resultUpdate()
    }
    
    //Calls this function when the tap is recognized. Code from: https://stackoverflow.com/questions/24126678/close-ios-keyboard-by-touching-anywhere-using-swift
    @objc func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }
    
    func doCalculos(FirstNum baseNum: Double, SecondNum powerNum: Double) -> Double{
        let result = pow(baseNum,powerNum)
        return result
    }
    
    func resultUpdate(){
        var mainNumber: Double
        var powerNumber: Double
        var finalResult: Double
        powerNumber = Double(powerStepper.value)
        
        if baseNumber.text!.isEmpty{
            mainNumber = 0.0
            //create a UIAlertController object
            let alert=UIAlertController(title: "Warning", message: "Please enter a base number to do the calculation", preferredStyle: UIAlertController.Style.alert)
            //create a UIAlertAction object for the button
            let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
            alert.addAction(cancelAction) //adds the alert action to the alert object
            let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: {action in
                self.powerStepper.value = 0
                self.powerLabel.text = "0 - Power"
                self.resultLabel.text = "0"
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
        else{
            mainNumber = Double(baseNumber.text!)!
        }
        finalResult = doCalculos(FirstNum: mainNumber, SecondNum: powerNumber)
        resultLabel.text = String(format: "%.0f", finalResult)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        resultUpdate()
    }
    
    override func viewDidLoad() {
        baseNumber.delegate = self
        super.viewDidLoad()
        //Looks for single or multiple taps. Code from: https://stackoverflow.com/questions/24126678/close-ios-keyboard-by-touching-anywhere-using-swift
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIInputViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }


}

