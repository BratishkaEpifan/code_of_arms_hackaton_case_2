//
//  MyBankViewController.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 08.07.2022.
//

import UIKit

class MyBankViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
		view.backgroundColor = .systemBackground
		configureNC()
    }

	func configureNC() {
		let personImage = UIImage(systemName: "person.circle.fill")
		let button = UIBarButtonItem(image: personImage,
									 style: .plain,
									 target: nil, action: #selector(doSomeThing))
		let bellImage = UIImage(systemName: "bell")
		let buttonN = UIBarButtonItem(image: bellImage,
									  style: .plain,
									  target: nil, action: #selector(doSomeThing))
		let searchBar:UISearchBar = UISearchBar(frame: CGRect(x: 0, y: 0, width: 270, height: 20))
		searchBar.placeholder = "Поиск в Jusan"
		let leftNavBarButton = UIBarButtonItem(customView:searchBar)
		UINavigationBar.appearance().tintColor = .systemGray
		navigationItem.rightBarButtonItems = [button, buttonN]
		navigationItem.leftBarButtonItem = leftNavBarButton
	}
	
	@objc func doSomeThing() {}
}
