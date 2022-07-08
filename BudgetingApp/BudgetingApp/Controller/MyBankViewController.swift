//
//  MyBankViewController.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 08.07.2022.
//

import UIKit

class MyBankViewController: UIViewController {
    
    let button = UIButton()

    override func viewDidLoad() {
        super.viewDidLoad()
		view.backgroundColor = .systemBackground
		configureNC()
        configureButton()
    }
    
    func configureButton() {
        view.addSubview(button)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.backgroundColor = .red
        button.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        button.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
        button.addTarget(self, action: #selector(buttonPressed), for: .touchUpInside)
    }
    
    
    @objc func buttonPressed() {
        let mainVC = MainViewController()
        mainVC.hidesBottomBarWhenPushed = true
        self.navigationController?.pushViewController(mainVC, animated: true)
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
