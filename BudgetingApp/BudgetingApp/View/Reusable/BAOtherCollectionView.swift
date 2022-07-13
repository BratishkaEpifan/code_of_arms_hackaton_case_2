//
//  BAOtherCollectionView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 13.07.2022.
//

import UIKit

class BAOtherCollectionView: UIView, UICollectionViewDelegate, UICollectionViewDataSource {
	
	let collectionView: UICollectionView = {
		let layout = UICollectionViewFlowLayout()
		layout.sectionInset = UIEdgeInsets(top: 2, left: 30, bottom: 15, right: 30)
		layout.itemSize = CGSize(width: 150, height: 180)
		let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
		collectionView.translatesAutoresizingMaskIntoConstraints = false
		return collectionView
	}()
    var myDogObjects: [(String, String, String, String)] = [MyFanctions.returnCategories(cat: .games), MyFanctions.returnCategories(cat: .noCat), MyFanctions.returnCategories(cat: .taxi), MyFanctions.returnCategories(cat: .travel), MyFanctions.returnCategories(cat: .medicine), MyFanctions.returnCategories(cat: .restaurants), MyFanctions.returnCategories(cat: .cinema), MyFanctions.returnCategories(cat: .furniture), MyFanctions.returnCategories(cat: .clothes), MyFanctions.returnCategories(cat: .fitness), MyFanctions.returnCategories(cat: .beauty)]
        
    var myCatObjects: [(String, String, String, String)] = [] {
        didSet {
            for i in myCatObjects {
                var index = -1
                for j in 0..<myDogObjects.count {
                    if i.0 == myDogObjects[j].0 {
                        index = j
                    }
                }
                if index != -1 {
                    myDogObjects.remove(at: index)
                }
            }
            collectionView.reloadData()
        }
    }
    var myCategories: [String] = [] {
        didSet {
            for i in 0..<myCategories.count {
                switch myCategories[i] {
                case Categories.beauty.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .beauty))
                case Categories.cinema.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .cinema))
                case Categories.clothes.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .clothes))
                case Categories.fitness.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .fitness))
                case Categories.furniture.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .furniture))
                case Categories.games.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .games))
                case Categories.medicine.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .medicine))
                case Categories.restaurants.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .restaurants))
                case Categories.taxi.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .taxi))
                case Categories.travel.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .travel))
                case Categories.noCat.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .noCat))
                default: continue
                }
            }
        }
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        backgroundColor = .systemGray6
        configure()
        getData()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func getData() {
        NetworkManager.shared.getCategories(path: "current-combination"){ [weak self] result in
            switch result {
            case .success(let myCats): self?.myCategories = myCats
            case .failure: print("No Data")
            }
            self?.collectionView.reloadData()
        }
    }
    
    func configure() {
        addSubview(collectionView)
        collectionView.backgroundColor = .systemGray6
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.register(BAOtherCollectionViewCell.self,
                                forCellWithReuseIdentifier: BAOtherCollectionViewCell.cellID)
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: topAnchor),
            collectionView.leadingAnchor.constraint(equalTo: leadingAnchor),
            collectionView.trailingAnchor.constraint(equalTo: trailingAnchor),
            collectionView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        myDogObjects.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: BAOtherCollectionViewCell.cellID, for: indexPath) as! BAOtherCollectionViewCell
        cell.setup(myDogObjects[indexPath.row])
        return cell
    }
}
