//
//  ViewController.swift
//  Lucrum
//
//  Created by LUCAS GAMA on 9/30/21.
//

import UIKit

class Stock { //this class has the intention to creates an object Stock to store all stock information
    var name: String
    var numShares: Double
    var price: Double
    var stockTotalValue: Double
    var stockCategory: String
    var stockPercentage: Double
    
    init(name: String, numShares: Double, price: Double, stockCategory: String, stockTotalValue: Double, stockPercentage: Double) {
        self.name = name
        self.numShares = numShares
        self.price = price
        self.stockTotalValue = stockTotalValue
        self.stockCategory = stockCategory
        self.stockPercentage = stockPercentage
    }
}


class ViewController: UIViewController, UITextFieldDelegate, UITableViewDataSource {
    @IBOutlet weak var stockName: UITextField!
    @IBOutlet weak var sharesNum: UITextField!
    @IBOutlet weak var sharesPrice: UITextField!
    @IBOutlet weak var category: UITextField!
    @IBOutlet weak var stockTableView: UITableView!
    var stockArr:[Stock] = []
    var tableViewDataTitle = [String]()
    var tableViewDataPercentage = [Double]()
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return stockArr.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCell(withIdentifier: "TableViewCell", for: indexPath)
        cell = UITableViewCell(style: .value1, reuseIdentifier: "TableViewCell")
        cell.textLabel?.text = self.stockArr[indexPath.row].name
        cell.detailTextLabel?.text = String(self.stockArr[indexPath.row].stockPercentage) + "%"
        return cell
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    //Calls this function when the tap is recognized. Code from: https://stackoverflow.com/questions/24126678/close-ios-keyboard-by-touching-anywhere-using-swift
    @objc func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }
    
    func calcPercentage(){
        var percentage = 0.0
        var portifolioTotalValue = 0.0
        
        //calculate total value of portifolio
        for i in stockArr{
            portifolioTotalValue = portifolioTotalValue + i.stockTotalValue
        }
        
        for (index, element) in stockArr.enumerated(){
            percentage = (element.stockTotalValue/portifolioTotalValue)*100
            element.stockPercentage = percentage
        }
    }
    
    func addStock(){ //this function is responsible for adding the inputed Stocks info into an array of objects type Stock
        var stockTotalValue = 0.0
        if (category.text!.isEmpty){ //Category is not required field. Thus, if empty, create a category based on the stock name
            category.text =  stockName.text!
        }
        
        //calculate total value of this stock
        stockTotalValue = Double(sharesNum.text!)! * Double(sharesPrice.text!)!
        
        let stockHolder = Stock(name: String(stockName.text!), numShares: Double(sharesNum.text!)!, price: Double(sharesPrice.text!)!, stockCategory: category.text!, stockTotalValue: stockTotalValue, stockPercentage: 0.0)
        stockArr.append(stockHolder)
        
        //clear text fields
        stockName.text = ""
        sharesNum.text = ""
        sharesPrice.text = ""
        category.text = ""
        
    }
    
    @IBAction func addStockBt(_ sender: UIButton) {
        if(stockName.text!.isEmpty || sharesNum.text!.isEmpty || sharesPrice.text!.isEmpty){
            let alert=UIAlertController(title: "Warning", message: "Please fill out all required fields (Stock name, Number of Shares, and Price/share)", preferredStyle: UIAlertController.Style.alert)
            let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil)
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
        else{
            addStock()
        }
    }
    
    @IBAction func calculateBt(_ sender: UIButton) {
        calcPercentage()
        self.stockTableView.reloadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        stockName.delegate = self
        sharesNum.delegate = self
        sharesPrice.delegate = self
        category.delegate = self
        stockTableView.register(UITableViewCell.self, forCellReuseIdentifier: "TableViewCell")
        stockTableView.dataSource = self
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIInputViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
    
    }


}

